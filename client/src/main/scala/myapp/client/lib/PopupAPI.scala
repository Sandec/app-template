package myapp.client.lib

import de.sandec.jnodes.context.ContextManager
import de.sandec.jnodes.context.Util._
import simplefx.core._
import simplefx.all._

object PopupAPI extends PopupAPI {
  val popupNodeContext = new ContextManager[Node]
}
trait PopupAPI {


  def closePopup(node: Node): Unit = {
    val popup = PopupAPI.popupNodeContext.getContext(node)
    de.sandec.jnodes.context.Util.PageAndPopupOps(popup).closePopup(popup)
  }

  def showPopup(context: Node, node: Node): Unit = {
    PopupAPI.popupNodeContext.setContext(node,node)
    de.sandec.jnodes.context.Util.PageAndPopupOps(context).showPopup(node)
  }

  def setPopupNode(node: StackPane): Unit = de.sandec.jnodes.context.PopupContext.setContext(node, node)

}
