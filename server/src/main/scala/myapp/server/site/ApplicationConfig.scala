package myapp.server.site

import javax.ws.rs.ApplicationPath
import org.glassfish.jersey.server.ResourceConfig
import simplefx.core._

@ApplicationPath("/") class ApplicationConfig() extends ResourceConfig {
  System.out.println("Created ApplicationConfig!")


  //InitialData.generateInitialData

  packages("example.server")
}

