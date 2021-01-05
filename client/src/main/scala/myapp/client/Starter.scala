package myapp.client

import com.jpro.web.sessionmanager.SessionManager
import fr.brouillard.oss.cssfx.CSSFX
import simplefx.all._
import simplefx.core._

object Starter11 extends App {
  Starter.main(new Array(0))
}
object Starter extends App
@SimpleFXApp class Starter { THIS =>

  val app = new MyAppApp(stage)

  //CSSFX.start
  //CSSFX.start(stage)

  scene = new Scene(app,900,700)
  app.stylesheets ::= getClass.getResource("/myapp/client/css/base.css").toURI.toString

  app.start(SessionManager.getDefault(app,stage))
}