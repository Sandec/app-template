package myapp.client.pages

import com.jpro.web.Util.setLink
import simplefx.core._
import simplefx.all._

class SubPage extends DefaultPage {

  def description = "MyApp"
  def title = "MyApp"

  lazy val content = new Button("MainPage") {
    setLink(this,"/?page=main")
  }
}
