package backends.sales_dashboard.utils

object SchemaArgs {
  case class Period(start_date: String, end_date: String)

  sealed trait DayPart

  object DayPart {
    case object PT extends DayPart

    case object NPT extends DayPart
  }

  sealed trait ImpactRegular

  object ImpactRegular {
    case object IMPACT extends ImpactRegular

    case object REGULAR extends ImpactRegular
  }
  case class WebServer(ip_address:Option[String],port:Option[Int],secretKey:Option[String],allowedOrigins:Option[Set[String]])


  case class SPDInfo(
                      channel: List[String],
                      period: Period,
                      deviation_period: List[Period],
                      regions: List[String],
                      agency: Option[List[String]],
                      sub_agency: Option[List[String]],
                      pt_npt: List[DayPart],
                      advertiser_group: Option[List[String]],
                      deviation_advertiser_group: Option[List[String]],
                      impact_regular: Option[List[ImpactRegular]],
                      all_region_selected: Boolean,
                      all_advertiser_selected: Boolean,
                      all_agency_selected: Boolean,
                      all_sub_agency_selected: Boolean,
                      is_regional: Boolean
                    )


  case class InfoSchema(barc_year_week: String)

  case class FctFills(with_hul: Option[Double])

  case class FairShare(channel_grp: Option[Double])

  case class InfoFillsFairShareResult(info: Option[InfoSchema] = None, fills: Option[FctFills] = None, fair_share: Option[FairShare])


}
