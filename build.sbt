val scala3Version = "3.0.0"
import complete.DefaultParsers._
import sbt.complete._
import java.nio.file.{Files, Paths}
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import Dependencies._
lazy val root = project
  .in(file("."))
  .settings(
    name := "zio-http-scala3",
    version := "0.1.0",

    scalaVersion := scala3Version,
libraryDependencies ++= caliban,
libraryDependencies ++= zhttp,
libraryDependencies ++= postgres,
libraryDependencies ++= flyway,
libraryDependencies ++= rollbar,
libraryDependencies ++= logging,
libraryDependencies ++= poiExcel,
libraryDependencies ++= googleCloud
  )
