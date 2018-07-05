package rafaeltscs.marsrover

import rafaeltscs.marsrover.controller.TrafficController
import rafaeltscs.marsrover.exception.PlateauAlreadyDefinedException
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}
import rafaeltscs.marsrover.types.Types.Direction

import scala.util.matching.Regex

/**
  * Singleton object that controls the rovers and the plateau.
  */
object Commander {

//  private val COMMAND_PATTERN: Regex = "([aA-zZ]|[0-9])+ *([aA-zZ]|[0-9])*".r
  private var maybeTrafficController: Option[TrafficController] = None

  object Commands {
    object Patterns {
      val PLATEAU: Regex = "^Plateau:([0-9]+) ([0-9]+$)".r
      val LANDING: Regex = "^(\\w+) Landing:([0-9]+) ([0-9]+) ([NSEW]$)".r
      val INSTRUCTIONS: Regex = "^(\\w+) Instructions:([MLR]+$)".r
    }

    val MOVE_ROVER: Char = 'M'
    val TURN_ROVER_LEFT: Char = 'L'
    val TURN_ROVER_RIGHT: Char = 'R'
    val PROCEED: String = ""

    def isPlateauCommand(command: String): Boolean = {
      Patterns.PLATEAU.findFirstIn(command).isDefined
    }

  }

  def readCommands(orderFunction: => String, instructions : Seq[String] = Seq[String]()):  Seq[String] = {

    if(instructions.nonEmpty) {
      println(
        s"""
           |Current instructions:
           |\t${instructions.mkString("\n\t")}
        """.stripMargin
      )
    }

    print("Insert a new instruction or press ENTER to proceed: ")
    val order = orderFunction.trim

    if (order.isEmpty) {
      instructions
    } else {
      var validCommand = true

      order match {
        case Commands.Patterns.PLATEAU(x,y) =>
          if (instructions.nonEmpty) {
            println(s"A plateau has already been set. Instruction '$order' will be ignored.")
            validCommand = false
          }
        case Commands.Patterns.LANDING(roverName, x, y, direction) =>
          if(instructions.isEmpty){
            println(s"You need a Plateau in order to land a Rover. Instruction '$order' will be ignored.")
            validCommand = false
          } else if (instructions.exists(_.startsWith(roverName))) {
            println(s"There is already a instruction for landing a rover with the name '$roverName'. Instruction '$order' will be ignored.")
            validCommand = false
          }
        case Commands.Patterns.INSTRUCTIONS(roverName, cmdInstructions) =>
          if(instructions.isEmpty){
            println(s"You need a Plateau in order to move a Rover. Instruction '$order' will be ignored.")
            validCommand = false
          } else if (!instructions.exists(_.startsWith(roverName))) {
            println(s"There is no instruction for landing a rover with the name '$roverName'. Instruction '$order' will be ignored.")
            validCommand = false
          }
        case _ =>
          println("Invalid instruction.")
          validCommand = false
      }

      if (validCommand) readCommands(orderFunction, instructions.:+(order))
      else readCommands(orderFunction, instructions)
    }


  }

  def processCommands(commands: Seq[String]): Unit = {
    if(commands.isEmpty){
      println("No instructions provided. Shutting down the system...")
    }

    commands.foreach {
      case Commands.Patterns.PLATEAU(x, y) =>
        initPlateauController(x.toInt, y.toInt)
      case Commands.Patterns.LANDING(roverName, x, y, direction) =>
        maybeTrafficController.foreach { controller =>
          controller.landRover(roverName, Position(x.toInt, y.toInt, Direction.fromChar(direction.charAt(0))))
        }
      case Commands.Patterns.INSTRUCTIONS(roverName, cmdInstructions) =>
        maybeTrafficController.foreach { controller =>
          controller.moveRover(roverName,cmdInstructions)
        }
    }
  }

  def report: String = {
    maybeTrafficController.map{ controller =>
      controller.report
    }.getOrElse("There are no rovers landed.")
  }

  private def initPlateauController(width: Int, height: Int): Unit = {
    maybeTrafficController.foreach( _ => throw PlateauAlreadyDefinedException("A plateau has already been set."))
    maybeTrafficController = Option( TrafficController(Plateau(width,height)) )
  }

}
