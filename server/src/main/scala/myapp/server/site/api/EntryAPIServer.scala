package myapp.server.site.api

import java.security.{MessageDigest, SecureRandom}

import javax.ws.rs.{FormParam, GET, POST, Path, Produces, QueryParam}
import javax.ws.rs.core.MediaType
import myapp.server.sql.Tables._
import myapp.server.sql.Tables.config.profile.api._
import server.sql.APIServer.GetServer
import myapp.shared.Model.Entry
import myapp.shared.api.EntryAPI
import simplefx.core._


@Path("/api/v1/entry")
class EntryAPIServer extends EntryAPI {

  @GetServer
  def addEntry(x: String): Unit = {
    getResult(entries += Entry(None,x))
  }

  @GetServer
  def getEntries(): List[Entry] = {
    getResult(entries.result).toList
  }
}
