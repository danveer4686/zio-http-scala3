import com.rollbar.notifier.Rollbar
import backends.sales_dashboard.utils.RollBarContext

import cats.effect._
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import caliban.{CalibanError, GraphQLInterpreter}
import zio.{Task,ZIO,ZEnv}
import zio.interop.catz._
import zio.interop.catz.implicits._
import backends.sales_dashboard.{SalesDashboardApi, SalesDashboardService}

object CalibanHelpers {

  private val rollBar: Rollbar = RollBarContext()

  def dbResource: Resource[Task, HikariTransactor[Task]] = {
    for {
      ce <- ExecutionContexts.fixedThreadPool[Task](20)
      xa <- HikariTransactor.newHikariTransactor[Task](
        "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/postgres",
        "danveers", "", ce)
    } yield xa
  }

  def salesDashboardHttp4sInterpreter(transactor: HikariTransactor[Task]): ZIO[Any, CalibanError, GraphQLInterpreter[ZEnv, Throwable]] =
    SalesDashboardService.liveHttp4s(transactor,rollBar)
      .memoize
      .use {
        layer => SalesDashboardApi.api.interpreter.map(_.provideCustomLayer(layer))
      }

}
