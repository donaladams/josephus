name := """Josephus"""

version := "0.1.2"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "org.specs2" %% "specs2-core" % "3.8.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
