package backends.sales_dashboard.utils

import backends.sales_dashboard.utils.SchemaArgs._
import zhttp.http.CORSConfig

import scala.concurrent.duration._

object CorsConfig {
  def apply(config: Option[WebServer]): CORSConfig = {
    val origins = config.map(_.allowedOrigins.getOrElse(Set.empty)).getOrElse(Set.empty)
    if (origins.isEmpty)
      CORSConfig(anyOrigin = false, allowCredentials = false)
    else
      CORSConfig(anyOrigin = false, allowedOrigins = origins, allowCredentials = false)
  }
}
