import sbt._
object Dependencies {
  private val calibanVersion      = "0.10.1"
  private val zhttpVersion        = "1.0.0.0-RC17"
  private val zioInteropVersion   = "3.1.1.0"
  //private val jwtVersion        = "5.0.0"
  //private val scalacacheVersion = "0.28.0"
  private val doobieVersion       = "1.0.0-M3"

  private val googleCloudVersion = "1.98.0"
  private val rollBarVersion     = "1.3.1"
  private val flywayVersion      = "6.4.1"
  private val poiExcelVersion    = "3.17"

  private val TestContainerVersion  = "1.11.2"
  private val scalatestVersion      = "3.2.9"
  private val zioVersionTest        = "1.0.9"

  lazy val caliban = List(
  "com.github.ghostdogpr" %% "caliban" % calibanVersion,
    "com.github.ghostdogpr" %% "caliban-zio-http" %  calibanVersion
  )
  lazy val zhttp = List(
    "io.d11" %% "zhttp" % zhttpVersion
  )

  lazy val zioInterop = List(
  "dev.zio" %% "zio-interop-cats" % zioInteropVersion
  )

  lazy val doobie = List(
    "org.tpolecat" %% "doobie-core"     % doobieVersion ,
    "org.tpolecat" %% "doobie-postgres" % doobieVersion ,
    "org.tpolecat" %% "doobie-h2"       % doobieVersion ,
    "org.tpolecat" %% "doobie-hikari"   % doobieVersion

  )
  //  lazy val jwt = List(
  //    "com.pauldijou" %% "jwt-core" % jwtVersion
  //  )
  //  lazy val scalacache = List(
  //    "com.github.cb372" %% "scalacache-redis" % scalacacheVersion,
  //    "com.github.cb372" %% "scalacache-cats-effect" % scalacacheVersion
  //  )

  lazy val logging = List(
    "org.slf4j" % "slf4j-api" % "1.7.5",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
  lazy val flyway = List(
    "org.flywaydb" % "flyway-core" % flywayVersion
  )
  lazy val rollbar = List(
    "com.rollbar" % "rollbar-java" % rollBarVersion
  )
  lazy val googleCloud = List(
    "com.google.cloud" % "google-cloud-storage" % googleCloudVersion,
    "com.google.cloud" % "google-cloud-bigquery" % googleCloudVersion
  )

  lazy val poiExcel = List(
    "org.apache.poi" % "poi-ooxml" % poiExcelVersion
  )
}