package backends.sales_dashboard.cards

import backends.sales_dashboard.utils.SchemaArgs._
import zio.blocking.Blocking
import zio.{RIO, Task}

object InfoFillsFairShare {

  def apply(args: SPDInfo)
  :RIO[Blocking,InfoFillsFairShareResult]={

    println("#"*50 + "\nInfo fills started\n" + "#"*50)

    val info_task                    = RIO(  InfoSchema("barc_year_week") )
    val fills_task                   = RIO(  FctFills(Some(2.02)) )
    val fair_share_task              = RIO(  FairShare(Some(3.03)) )

    val res = for{
      ((info,fills),fair_share)   <- info_task.zipPar(fills_task).zipPar(fair_share_task)
      final_res                   = InfoFillsFairShareResult(Some(info), Some(fills), Some(fair_share) )
    } yield final_res

    println("#"*50 + "\nInfo fills ended\n" + "#"*50)

    res
  }
}