import Dependencies._

ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "employes_management",
    libraryDependencies ++= Seq(
      "org.mongodb.scala" %% "mongo-scala-driver" % "4.4.0",
      "org.scalafx" %% "scalafx" % "15.0.1-R20",
      "org.openjfx" % "javafx-fxml" % "16",
      "org.openjfx" % "javafx-controls" % "16",
      "org.apache.pdfbox" % "pdfbox" % "2.0.24"
    ),
    mainClass in Compile := Some("gui.EmployeeGUI")
  )
