package backends.examples

import zhttp.http.{HttpApp, Response}
import zio.{App, ExitCode, URIO, clock}
import zhttp.http._
import zhttp.service.Server
import zio._

object HelloWorldAdvanced extends App {

  private val fooBar: HttpApp[Any, Nothing] = HttpApp.collect {
    case Method.GET -> Root / "foo" => Response.text("bar")
    case Method.GET -> Root / "bar" => Response.text("foo")
  }

  private val app = HttpApp.collectM {
    case Method.GET -> Root / "random" => random.nextString(10).map(Response.text)
    case Method.GET -> Root / "utc" => clock.currentDateTime.map(s => Response.text(s.toString))
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = Server.start(8090, (fooBar +++ app).silent).exitCode

}
