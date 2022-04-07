val ScalaTestVersion = "3.2.11"
val ScalaCheckVersion = "1.15.4"
val ScalaTestPlusScalaCheckVersion = "3.2.11.0"

lazy val root = (project in file("."))
  .settings(
    name := "shopping-cart",
    version := "1.0.0",
    scalaVersion := "3.1.1",
    libraryDependencies ++= Seq(
      "org.scalatest"    %% "scalatest"        % ScalaTestVersion               % Test,
      "org.scalacheck"   %% "scalacheck"       % ScalaCheckVersion              % Test,
      "org.scalatestplus" %% "scalacheck-1-15" % ScalaTestPlusScalaCheckVersion % Test
    )
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-explaintypes",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
  "-feature",
  "-Xfatal-warnings"
)
