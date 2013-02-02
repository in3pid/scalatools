import sbt._
import sbt.Keys._

object LabBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val lab = Project(
    id = "lab",
    base = file("."),
    settings = buildSettings ++ List(
      name := "Lab",
      libraryDependencies ++= List(
        akka,
        scalaTest)))
}

object BuildSettings {

  val buildOrganization = "mh"
  val buildVersion = "0.0.1-SNAPSHOT"
  val buildScalaVersion = "2.10.0"

  val buildSettings = Project.defaultSettings ++ Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    resolvers ++= Repositories.all,
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-feature"))
}

object Repositories {
  val typeSafeRepo = "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases"
  val sonaSnap = "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  val all = Seq(typeSafeRepo, sonaSnap)
}

object Dependencies {
  val akka = "com.typesafe.akka" % "akka-actor_2.10" % "2.1.0"
  val scalaTest = "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
}
