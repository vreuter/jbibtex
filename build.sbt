name := "jbibtex"
version := "1.0.17"
scalaVersion := "2.12.9"
organization := "jbibtex"

assemblyJarName in assembly := s"${name.value}_v${version.value}.jar"
publishTo := Some(Resolver.file(s"${name.value}",  new File(Path.userHome.absolutePath + "/.m2/repository")))

/* Jackson and JUnit dependencies translated to sbt from jbibtex POM */
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.3"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.3"
libraryDependencies += "junit" % "junit" % "4.12" % "test"

/*
libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.0"
libraryDependencies += "org.typelevel" % "mouse_2.12" % "0.23"
//libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.22.0"
*/

resolvers += Resolver.mavenLocal
libraryDependencies += "vreuter" %% "autoepisteme" % "0.0.1-SNAPSHOT"

/*
libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
libraryDependencies += "eu.timepit" %% "refined" % "0.9.4"
libraryDependencies += "org.typelevel" %% "kittens" % "1.2.0"
libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3"
)
*/

/* Java and compiler options */
scalacOptions ++= Seq("-deprecation", "-feature", "-Ypartial-unification")
//javaOptions += "-Xmx4G"

/* ScalaTest options */
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oS")    // o for stdout, S for "short" stack trace; "F" for full
parallelExecution in Test := false                                        // Run tests serially for more intelligible exec output.

lazy val root  = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoKeys := Seq[BuildInfoKey](name, version), buildInfoPackage := "jbibtexinfo")

// Enable quitting a run without quitting sbt.
cancelable in Global := true

excludeFilter in unmanagedSources := HiddenFileFilter || "Interactive*.scala" || ( new FileFilter { def accept(f: File) = Set("reserved")(f.getParentFile.getName) } )

