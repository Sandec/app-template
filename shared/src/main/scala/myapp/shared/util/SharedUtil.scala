package myapp.shared.util

import scala.concurrent.{Future, blocking}
import simplefx.core._
import simplefx.experimental._
import scala.concurrent.ExecutionContext.Implicits.global

object SharedUtil {

  def call[T](expr: => T): Future[T] = Future(blocking(expr))

  def callFX[T](expr: => T): FXFuture[T] = {
    val f = call(expr)
    f.onFailure {
      case e => e.printStackTrace()
    }
    f.asFX
  }
}