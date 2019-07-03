package myapp.shared

object Model {

  case class ID[T](value: Int)

  case class Entry(id: Option[ID[Entry]], text: String)
}
