package backends

import backends.sales_dashboard.utils.SchemaArgs._
import zio.blocking.Blocking
import zio.{Has, ZIO}

package object sales_dashboard {
  object SalesDB {
    trait Service {
      def getInfoFillsFairShareData(args:SPDInfo, list_of_selections: List[String])
      :ZIO[SalesDashboardServiceBlocking,Throwable,InfoFillsFairShareResult]
    }
  }
  type SalesDashboardAkka = Has[SalesDB.Service]
  type SalesDashboardServiceBlocking = Has[SalesDB.Service] with Blocking
  def getInfoFillsFairShareData(args:SPDInfo, list_of_selections: List[String])
  =
    ZIO.accessM[SalesDashboardServiceBlocking](_.get.getInfoFillsFairShareData(args, list_of_selections))
}
