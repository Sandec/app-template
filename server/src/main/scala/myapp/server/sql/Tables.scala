package myapp.server.sql

import myapp.shared.Model._
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.migration.api.{Dialect, GenericDialect}

object Tables extends SQLUtil {

  val config: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("myapp.db")
  implicit val db = config.db

  import config.profile.api._

  val dialect: Dialect[_] = GenericDialect(config.profile)

  class Entries(tag: Tag) extends Table[Entry](tag, "ENTRY") {
    def id = column[ID[Entry]]("id", O.PrimaryKey, O.AutoInc)
    def text = column[String]("text")

    def * = (id.?, text) <> (Entry.tupled, Entry.unapply)
  }
  val entries = TableQuery[Entries]


  createTableifNotExisting(entries)
}
