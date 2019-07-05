package myapp.client

import org.fxmisc.cssfx.CSSFX
import simplefx.all._
import simplefx.core._

object Starter extends App
@SimpleFXApp class Starter { THIS =>

  val app = new MyAppApp(stage)

  CSSFX.start

  scene = new Scene(app,900,700)
  app.stylesheets ::= getClass.getResource("/myapp/client/css/base.css").toURI.toString

  app.start()
}