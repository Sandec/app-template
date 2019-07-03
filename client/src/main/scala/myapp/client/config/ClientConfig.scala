package myapp.client.config

import com.typesafe.config.ConfigFactory

object ClientConfig {
  private lazy val config = ConfigFactory.load()

  val apiserver = config.getString("myapp.apiserver")
}
