package backends.sales_dashboard

import backends.sales_dashboard.cards.InfoFillsFairShare
import caliban.CalibanError.ExecutionError
//import cats.effect.Blocker
import com.rollbar.notifier.Rollbar
import doobie.hikari.HikariTransactor
import org.slf4j.{Logger, LoggerFactory}
import backends.sales_dashboard.utils.RollBarContext
import zio.{Task, ZIO, ZLayer}
import backends.sales_dashboard.utils.RollBarContext.logCustomErrorInRollbar
import backends.sales_dashboard.utils.SchemaArgs.{InfoFillsFairShareResult, SPDInfo}

import scala.concurrent.ExecutionContext

object SalesDashboardService {

  def liveHttp4s(pgTransactor: HikariTransactor[Task], rollBar: Rollbar): ZLayer[Any, Throwable, SalesDashboardAkka] =
    ZLayer.fromEffect(Task(SalesDashboardService(rollBar, pgTransactor)))
}

final case class SalesDashboardService(rollBarContext:Rollbar, trans : HikariTransactor[Task]) extends SalesDB.Service {
  val logger: Logger = LoggerFactory.getLogger(getClass.getName)
  def getInfoFillsFairShareData(args:SPDInfo, list_of_selections: List[String])
  :ZIO[SalesDashboardServiceBlocking,Throwable,InfoFillsFairShareResult] =   {
    val t1 = System.nanoTime
    try {
      for {
        t <- InfoFillsFairShare(args)
        _ <- Task{logger.info(s"getInfoFillsFairShareData Api took ${(System.nanoTime - t1) / 1e9d}")}
      } yield t
    }
    catch {
      case e: Throwable =>
        logger.info(s"getInfoFillsFairShareData Api took ${(System.nanoTime - t1) / 1e9d}")
        logCustomErrorInRollbar(rollBarContext, e, "getInfoFillsFairShareData", args.toString)
        throw e
    }
  }.mapError { e =>
    logger.error(e.getMessage)
    ExecutionError(e.getMessage)
  }

}