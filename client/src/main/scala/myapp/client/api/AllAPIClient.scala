package myapp.client.api

import myapp.shared.api.AllAPI

object AllAPIClient extends AllAPI {

  val entry = new EntryAPIClient
}
