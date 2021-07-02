import caliban.ZHttpAdapter
import zhttp.http.Method.{GET, POST}
import zhttp.http._
import zhttp.service.Server
import zio.stream._
import backends.sales_dashboard.SalesDashboardApi

trait GQLServerHttp {

//  val staticRoutes = HttpApp.collect {
//    case _ -> Root / "about" => Response.text(s"Hello, Welcome to sales_dashboard API, Built with scala version 3.0.0")
//  }
//
//  def spdServer()  =
//    (for {
//      dbTransactor   <- CalibanHelpers.dbResource
//      //spdInterpreter <- SalesDashboardApi.api.interpreter
//      spdInterpreter <- CalibanHelpers.salesDashboardHttp4sInterpreter(dbTransactor)
//      port           = 8080
//      _              <- Server.start(
//        port,
//        staticRoutes +++
//        Http.route {
//          case _ -> Root / "api" / "graphql" => CORS(ZHttpAdapter.makeHttpService(spdInterpreter))
//          }
//      ).forever
//    } yield ()).forever
}