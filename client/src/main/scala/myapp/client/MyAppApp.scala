package myapp.client

import com.jpro.web.{Redirect, WebApp}
import myapp.client.pages.MainPage
import simplefx.all.Stage

class MyAppApp(stage: Stage) extends WebApp(stage) {
  addRoute { case ""                => new Redirect("/?page=main")}
  addRoute { case "/"               => new Redirect("/?page=main")}
  addRoute { case "/?page=main"  => new MainPage() }
}
