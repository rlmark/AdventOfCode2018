name := "AdventOfCode2018"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies := Seq(
  "org.typelevel" %% "cats-core" % "1.5.0-RC1",
  "org.scalatest"         %% "scalatest"            % "3.0.5" % Test,
  "org.mockito"           % "mockito-core"          % "2.18.3" % Test
)
scalacOptions += "-Ypartial-unification"
