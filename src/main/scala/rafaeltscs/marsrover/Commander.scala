package rafaeltscs.marsrover

import rafaeltscs.marsrover.controller.PlateauController
import rafaeltscs.marsrover.exception.PlateauAlreadyDefinedException
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/**
  * Singleton object that controls the rovers and the plateau.
  */
object Commander {

//  private val COMMAND_PATTERN: Regex = "([aA-zZ]|[0-9])+ *([aA-zZ]|[0-9])*".r
  private var plateauController: Option[PlateauController] = None
  private val rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()

  object Commands {
    val PLATEAU_PATTERN: Regex = "^Plateau:([0-9]+) ([0-9]+$)".r
    val LANDING: String = "Landing"
    val INSTRUCTIONS: String = "Instructions"
    val MOVE_ROVER: Char = 'M'
    val TURN_ROVER_LEFT: Char = 'L'
    val TURN_ROVER_RIGHT: Char = 'R'
    val PROCEED: String = ""

    def isPlateauCommand(command: String): Boolean = {
      PLATEAU_PATTERN.findFirstIn(command).isDefined
    }
  }

  def command(order: => String, instructions : Seq[String] = Seq[String]()) {
    if(instructions.nonEmpty) {
      println(
        s"""
           |Current instructions:
           |\t${instructions.mkString("\n\t")}
        """.stripMargin
      )
    }

    print("Type an instruction or press ENTER to proceed: ")
    order match {
      case Commands.PROCEED =>
        processCommands(instructions)
        println("finished.")
      case str: String =>
        if(instructions.exists(_ => Commands.isPlateauCommand(str))) {
          println(s"A plateau has already been set. Instruction '$str' will be ignored.")
          command(order, instructions)
        } else {
          command(order, instructions.:+(str))
        }
    }
  }

  def processCommands(commands: Seq[String]): Unit = {
    if(commands.isEmpty){
      println("No instructions provided. Shutting down the system...")
    }

    commands.foreach  {
      case Commands.PLATEAU_PATTERN(x,y) => println("plateau")
        initPlateauController(x.toInt,y.toInt)
    }
  }

  private def initPlateauController(width: Int, height: Int): Unit = {
    plateauController.foreach( _ => throw PlateauAlreadyDefinedException("A plateau has already been set."))
    plateauController = Option(PlateauController(Plateau(width,height)))
  }

  private def landRover(name: String, position: Position) = {

  }
}
