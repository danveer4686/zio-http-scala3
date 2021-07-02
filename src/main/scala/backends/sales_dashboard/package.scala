package backends

import zio.Has
import zio.ZIO
import zio.blocking.Blocking
import sales_dashboard.utils.SchemaArgs._

package object sales_dashboard {

  type SalesDashboardAkka = Has[SalesDB.Service]
  type SalesDashboardServiceBlocking = Has[SalesDB.Service] with Blocking

  object SalesDB {
    trait Service {
      def getInfoFillsFairShareData(args: SPDInfo, list_of_selections: List[String])
      : ZIO[SalesDashboardServiceBlocking, Throwable, InfoFillsFairShareResult]
    }
  }

  def getInfoFillsFairShareData(args:SPDInfo, list_of_selections: List[String])
  = ZIO.accessM[SalesDashboardServiceBlocking](_.get.getInfoFillsFairShareData(args, list_of_selections))

    }
