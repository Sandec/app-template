package myapp.server.sql

import remoting.stake.Types.EntityOps
import simplefx.util.Predef.SourceLocation
import slick.jdbc.meta.MTable
import slick.sql.SqlAction
import myapp.shared.Model.ID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import simplefx.core._
import slick.migration.api.{Dialect, GenericDialect, H2Dialect, TableMigration}

trait SQLUtil {
  this: Tables.type =>

  import config.profile.api._

  implicit def idTypeSupport[T] = {
    MappedColumnType.base[ID[T], Int](
      { b => b.value }, // map Bool to Int
      { i => ID[T](i) } // map Int to Bool
    )
  }

  def getFuture[R](query: SqlAction[R, NoStream, Nothing])(implicit loc: SourceLocation): Future[R] = {
    val f = db.run(query)
    f.onFailure { case e => e.printStackTrace(); throw e }
    f
  }

  def getResult[R](query: SqlAction[R, NoStream, Nothing])(implicit loc: SourceLocation): R = try {
    val start = systemTime
    val res = Await.result(getFuture(query), 10 s)
    val timeTaken = systemTime - start
    if (timeTaken > (3 s)) {
      println(s"The query from ${loc} did take long: $timeTaken")
      println(query.statements.toList)
    }
    res
  } catch {
    case e: Throwable =>
      println(s"query from ${loc} took too at least 10 seconds: " + query.statements.toList)
      throw e
  }


  def getFuture2[R](query: DBIOAction[R, NoStream, Nothing]): Future[R] = {
    val f = db.run(query)
    f.onFailure { case e => e.printStackTrace(); throw e }
    f
  }

  def getResult2[R](query: DBIOAction[R, NoStream, Nothing]): R = {
    Await.result(getFuture2(query), minute * 1)
  }

  def getMTable[T <: Table[_]](_table: TableQuery[T]) = {
    val tables = getResult2(MTable.getTables).toList
    println("tables: " + tables)
    tables.filter { x =>
      x.name.name.toLowerCase == _table.baseTableRow.tableName.toLowerCase
    }.headOption
  }

  def createTableifNotExisting[T <: Table[_]](_table: TableQuery[T]): Boolean = {
    if (getMTable(_table).isEmpty) {
      println(_table.baseTableRow.tableName + " table not present")
      val setup = _table.schema.create
      getResult2(setup)
      true
    }
    else {
      println(_table.baseTableRow.tableName + " table present!!")
      false
    }
  }

  //def getMTable[T <: Table[_]](table: TableQuery[T]) = {
  //  val tables = getResult2(MTable.getTables).toList
  //  println("tables: " + tables)
  //  tables.filter{ x =>
  //    x.name.name.toLowerCase == table.baseTableRow.tableName.toLowerCase
  //  }.headOption
  //}
  def addColumnToTable[T <: Table[_]](table: TableQuery[T])(name: String, f: T => Rep[_]) = {
    val mTable = getMTable(table)
    mTable.map { mTable =>
      val getColumnNames = getResult2(mTable.getColumns).map(_.name).toList

      if (!getColumnNames.map(_.toLowerCase).contains(name.toLowerCase)) {
        val migration = TableMigration(table)(dialect)
          .addColumns(f)
        Await.result(db.run(migration()), 1 * minute)
      }
    }
  }
}