package myapp.client.pages

import simplefx.core._
import simplefx.all._

class MainPage extends DefaultPage { THIS =>

  def description = "MyApp"
  def title = "MyApp"

  lazy val content = new VBox {
    new Label("Headline")
  }
}
