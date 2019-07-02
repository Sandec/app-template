package myapp.client

import simplefx.all._
import simplefx.core._

object Starter extends App
@SimpleFXApp class Starter { THIS =>

  val app = new MyAppApp(stage)

  scene = new Scene(app,900,700)
  app.stylesheets ::= "/myapp/css/myapp.css"

  app.start()
}