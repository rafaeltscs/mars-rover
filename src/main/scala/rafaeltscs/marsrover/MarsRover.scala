package rafaeltscs.marsrover

import rafaeltscs.marsrover.buildinfo.BuildInfo

import scala.io.StdIn

object MarsRover {
  def main(args: Array[String]): Unit = {
    println(s"Starting ${BuildInfo.name} - v${BuildInfo.version}")

    val instructions = Commander.readCommands(StdIn.readLine())
    Commander.processCommands(instructions)

    println(Commander.report)
  }
}