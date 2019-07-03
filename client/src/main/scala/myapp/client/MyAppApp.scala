package myapp.client

import com.jpro.web.{Redirect, WebApp}
import myapp.client.pages.MainPage
import simplefx.all._
import simplefx.core._

class MyAppApp(stage: Stage) extends WebApp(stage) {

  stylesheets ::= "/myapp/client/css/base.css"

  addRoute { case ""                => new Redirect("/?page=main")}
  addRoute { case "/"               => new Redirect("/?page=main")}
  addRoute { case "/?page=main"  => new MainPage() }
}
