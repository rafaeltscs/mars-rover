package rafaeltscs.marsrover

import rafaeltscs.marsrover.buildinfo.BuildInfo
import rafaeltscs.marsrover.exception._

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object MarsRover {
  def main(args: Array[String]): Unit = {
    println(s"Starting ${BuildInfo.name} - v${BuildInfo.version}")

    Try {
      val instructions = Commander.readCommands(StdIn.readLine())
      Commander.processCommands(instructions)
    } match {
      case Failure(f) =>
        f match {
          case _: DuplicateRoverException |
               _: NoRoverException |
               _: InvalidPositionException |
               _: PlateauAlreadyDefinedException =>
            println(f.getMessage)
          case _: CouldNotLandRoverException |
              _: CannotMoveROverException =>
            println(s"${f.getMessage}. ${f.getCause.getMessage}")
          case _ => f.printStackTrace()
        }
      case Success(_) =>
    }

    println(Commander.report)
  }
}