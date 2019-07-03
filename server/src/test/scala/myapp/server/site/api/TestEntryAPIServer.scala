package myapp.server.site.api

import org.junit.Test

class TestEntryAPIServer {

  @Test
  def simpleTests: Unit = {
    val initial = AllAPIServer.entry.getEntries()
    AllAPIServer.entry.addEntry("test")
    val after = AllAPIServer.entry.getEntries()
    assert(initial.length + 1 == after.length)
    assert(after.map(_.text).contains("test"))
  }

}
