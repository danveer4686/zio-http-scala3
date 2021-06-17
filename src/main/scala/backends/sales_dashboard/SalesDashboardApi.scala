package backends.sales_dashboard

import backends.sales_dashboard.utils.SchemaArgs.{InfoFillsFairShareResult, SPDInfo}
import caliban.{GraphQL, RootResolver}
import zio.ZIO
import zio.clock.Clock
import zio.console.Console
import caliban.GraphQL.graphQL

object SalesDashboardApi {

  case class Queries(info_fills_market_share_data: SPDInfo => ZIO[SalesDashboardServiceBlocking,Throwable, InfoFillsFairShareResult])
  var list_of_selections:List[String] = List()

  val api: GraphQL[Console with Clock with SalesDashboardServiceBlocking] =
    graphQL(
      RootResolver(
        Queries(
          args => getInfoFillsFairShareData(args,list_of_selections)
        )
      )
    )

}
