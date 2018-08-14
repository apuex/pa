import Dependencies._

name := "wincom-utility"
scalaVersion := scalaVersionNumber
organization := mstarGroupName
version      := artifactVersionNumber

libraryDependencies ++= Seq(
  slf4jApi % Test,
  slf4jSimple % Test,
  scalaTest % Test,
  scalaTesplusPlay % Test
)


publishTo := localRepo

