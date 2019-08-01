package myapp.server.site.api

import myapp.shared.api.AllAPI

object AllAPIServer extends AllAPI {

  val entry = new EntryAPIServer

}
