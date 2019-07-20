package myapp.client.util

import myapp.client.lib.PopupAPI
import simplefx.core._
import simplefx.all._


object Popup extends PopupAPI{

  class DefaultPopup(x: Node) extends StackPane {

    //fillHeight = true
    alignment = Pos.CENTER
    styleClass ::= "template-popup-background"

    padding = Insets(50)
    this <++ new ScrollPane {
      maxHeightProp = javafx.scene.layout.Region.USE_PREF_SIZE
      maxWidthProp = javafx.scene.layout.Region.USE_PREF_SIZE

      styleClass ::= "template-popup-container"
      content = new StackPane {
        this <++ x
      }
    }
  }

  def infoPopup(text: String) = new DefaultPopup(new VBox {
    this <++ new Label(text)
    this <++ new Button("Close") {
      onAction --> {
        println("calling close")
        PopupAPI.closePopup(this)
      }
    }
  })
}
