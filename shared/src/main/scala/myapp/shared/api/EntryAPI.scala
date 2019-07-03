package myapp.shared.api

import myapp.shared.Model.Entry

trait EntryAPI {

  def addEntry(x: String): Unit
  def getEntries(): List[Entry]

}
