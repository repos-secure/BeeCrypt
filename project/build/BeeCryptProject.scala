import sbt._

/**
 * (c) 2011 ...
 *
 * User: zvozin
 * Date: 6/19/11
 * Time: 1:01 PM
 */

class BeeCryptProject(info: ProjectInfo) extends DefaultProject(info) {

  override def libraryDependencies = Set(/**
                                          * Testing
                                          */
                                         "junit" % "junit" % "4.5" % "test",
                                         "org.scala-tools.testing" %% "specs" % "1.6.6" % "test"
                                        ) ++ super.libraryDependencies
}