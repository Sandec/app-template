package myapp.client.pages

import myapp.client.api.AllAPIClient
import myapp.shared.util.SharedUtil._
import simplefx.core._
import simplefx.all._

class MainPage extends DefaultPage { THIS =>

  def description = "MyApp"
  def title = "MyApp"

  def addEntry(x: String): Unit = {
    callFX(AllAPIClient.entry.addEntry(x)).map { _ =>
      updateEntries()
    }

  }
  def updateEntries(): Unit = {
    callFX(AllAPIClient.entry.getEntries()).map { entries =>
      entriesNode.children = entries.map(x => new Label(x.text) {
        styleClass ::= "entry"
      })
    }
  }
  updateEntries()

  lazy val content = new VBox {
    styleClass ::= "vbox"
    this <++ new HBox {
      this <++ new Label("Add entries:")
      this <++ new TextField() {
        onAction --> addEntry(this.text)
      }
    }
    this <++ entriesNode
  }

  lazy val entriesNode: VBox = new VBox ()
}
