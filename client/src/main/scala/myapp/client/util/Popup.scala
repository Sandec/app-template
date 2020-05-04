package myapp.client.util

import myapp.client.lib.PopupAPI
import simplefx.core._
import simplefx.all._
import simplefx.experimental._


object Popup extends PopupAPI{

  class DefaultPopup extends StackPane { POPUP =>

    @Bind var title = "Title"
    @Bind var buttons = List.empty[Button]
    @Bind var content: Node = Group()
    //fillHeight = true
    alignment = Pos.CENTER
    styleClass ::= "template-popup-background"

    padding = Insets(50)
    this <++ new ScrollPane {
      maxHeightProp = javafx.scene.layout.Region.USE_PREF_SIZE
      maxWidthProp = javafx.scene.layout.Region.USE_PREF_SIZE

      styleClass ::= "template-popup-container"
      this.content = new VBox {
        this <++ new Label {
          styleClass ::= "template-popup-title-label"
          text <-- title
        }

        this <++ new StackPane {
          javafx.scene.layout.VBox.setVgrow(this, Priority.ALWAYS)
          children <-- List(POPUP.content)
        }

        this <++ new HBox {
          styleClass ::= "template-popup-buttons"
          children <-- buttons
        }
      }
    }
  }

  def infoPopup(_title: String, text: String) = new DefaultPopup{
    this.title = _title
    buttons <++ new Button("Close") {
      onAction --> {
        println("calling close")
        PopupAPI.closePopup(this)
      }
    }
    content = new Label(text)
  }
}
