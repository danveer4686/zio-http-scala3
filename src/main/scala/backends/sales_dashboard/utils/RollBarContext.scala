package backends.sales_dashboard.utils

import com.rollbar.notifier.Rollbar
import zio.Task

import java.io.{PrintWriter, StringWriter}
import scala.collection.JavaConverters._

object RollBarContext {
  def apply(): Rollbar = {
    import com.rollbar.notifier.config.ConfigBuilder
    val config = ConfigBuilder
      .withAccessToken("5115531ca47c45529c62a542e52aa4e6")
      .language("scala")
      .codeVersion("1.0.0")
      .environment("dev")
      .build()
    new Rollbar(config)
  }

  def logCustomErrorInRollbar(rollBarContext: Rollbar, e: Throwable, c1_func_name: String, c2_args: String): Unit = {
    val sw = new StringWriter
    e.printStackTrace(new PrintWriter(sw))

    rollBarContext.error(e.getMessage, Map(
      "c1_func_name" -> c1_func_name.asInstanceOf[java.lang.Object],
      "c2_args" -> c2_args.asInstanceOf[java.lang.Object],
      "c3_stackTrace" -> sw.toString.asInstanceOf[java.lang.Object]
    ).asJava
    )
  }
}
