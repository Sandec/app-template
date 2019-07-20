package myapp.client

import com.jpro.web.{Redirect, WebApp}
import myapp.client.pages._
import simplefx.all._
import simplefx.core._

class MyAppApp(stage: Stage) extends WebApp(stage) {

  lib.PopupAPI.setPopupNode(this)

  addRoute { case ""                => new Redirect("/?page=main")}
  addRoute { case "/"               => new Redirect("/?page=main")}
  addRoute { case "/?page=main"     => new MainPage() }
  addRoute { case "/?page=sub"      => new SubPage() }
}
