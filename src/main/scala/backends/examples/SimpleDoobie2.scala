package backends.examples

import cats.effect._
import doobie.implicits._
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import zio.Task
import zio.interop.catz._
import zio.interop.catz.implicits._
import doobie.Fragment

object SimpleDoobie2 extends zio.App {
   def run(args: List[String]) = {

    println("creating transector")
    // Resource[IO, HikariTransactor[IO]]
    val transactor: Resource[Task, HikariTransactor[Task]] = for {
      ce <- ExecutionContexts.fixedThreadPool[Task](32)
      xa <- HikariTransactor.newHikariTransactor[Task](
        "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/postgres",
        "danveers", "", ce)

    } yield xa

    val query = s"select col1 from public.spr"
    println("query is: " +query)
    val result = Fragment.const(query).query[String].unique

    val x = transactor.use { xa =>
      for {
        value <- result.transact(xa)
        _ = println(value)
      } yield ExitCode.Success
    }
     x.exitCode
  }
}