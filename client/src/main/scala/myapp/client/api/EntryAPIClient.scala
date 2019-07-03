package myapp.client.api


import myapp.client.config.ClientConfig
import myapp.shared.Model.Entry
import myapp.shared.api.EntryAPI
import remote.stake.client.APIClient.GetClient
import shapeless._
import syntax.singleton._
import record._

class EntryAPIClient extends EntryAPI with APIClientBase {

  def server = ClientConfig.apiserver + "/api/v1/entry/"

  @GetClient
  def addEntry(x: String): Unit = ???

  @GetClient
  def getEntries(): List[Entry] = ???

}
