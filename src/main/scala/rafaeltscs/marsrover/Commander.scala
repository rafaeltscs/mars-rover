package rafaeltscs.marsrover

import rafaeltscs.marsrover.controller.TrafficController
import rafaeltscs.marsrover.exception.PlateauAlreadyDefinedException
import rafaeltscs.marsrover.model.{Plateau, Position}
import rafaeltscs.marsrover.types.Types.{Command, Direction}

import scala.util.{Failure, Try}


/**
  * Singleton object that controls the rovers and the plateau.
  */
object Commander {

//  private val COMMAND_PATTERN: Regex = "([aA-zZ]|[0-9])+ *([aA-zZ]|[0-9])*".r
  private var maybeTrafficController: Option[TrafficController] = None

  def readCommands(orderFunction: => Command, instructions : Seq[Command] = Seq[Command]()):  Seq[Command] = {

    if(instructions.nonEmpty) {
      println(
        s"""
           |Current instructions:
           |\t${instructions.mkString("\n\t")}
        """.stripMargin
      )
    }

    print("Insert a new instruction or press ENTER to proceed: ")
    val command: Command = orderFunction.trim

    if (command.isEmpty) {
      instructions
    } else {
      var validCommand = true
      command match {
        case Command.Patterns.PLATEAU(x,y) =>
          if (instructions.nonEmpty) {
            println(s"A plateau has already been set. Instruction '$command' will be ignored.")
            validCommand = false
          }
        case Command.Patterns.LANDING(roverName, x, y, direction) =>
          if(instructions.isEmpty){
            println(s"You need a Plateau in order to land a Rover. Instruction '$command' will be ignored.")
            validCommand = false
          } else if (instructions.exists(_.startsWith(roverName))) {
            println(s"There is already a instruction for landing a rover with the name '$roverName'. Instruction '$command' will be ignored.")
            validCommand = false
          }
        case Command.Patterns.INSTRUCTIONS(roverName, cmdInstructions) =>
          if(instructions.isEmpty){
            println(s"You need a Plateau in order to move a Rover. Instruction '$command' will be ignored.")
            validCommand = false
          } else if (!instructions.exists(_.startsWith(roverName))) {
            println(s"There is no instruction for landing a rover with the name '$roverName'. Instruction '$command' will be ignored.")
            validCommand = false
          }
        case _ =>
          println("Invalid instruction.")
          validCommand = false
      }

      if (validCommand) readCommands(orderFunction, instructions.:+(command))
      else readCommands(orderFunction, instructions)
    }


  }

  def processCommands(commands: Seq[Command])= {
    if(commands.isEmpty){
      println("No instructions provided. Shutting down the system...")
    }

    commands.foreach {
      case Command.Patterns.PLATEAU(x, y) =>
        initPlateauController(x.toInt, y.toInt)
      case Command.Patterns.LANDING(roverName, x, y, direction) =>
        maybeTrafficController.map { controller =>
          controller.landRover(roverName, Position(x.toInt, y.toInt, Direction.fromChar(direction.charAt(0))))
        }
      case Command.Patterns.INSTRUCTIONS(roverName, cmdInstructions) =>
        maybeTrafficController.map { controller =>
          controller.moveRover(roverName, cmdInstructions)
        }
    }

  }

  def report: String = {
    maybeTrafficController.map{ controller =>
      controller.report
    }.getOrElse("There are no rovers landed.")
  }

  private def initPlateauController(upperX: Int, upperYt: Int): Unit = {
    maybeTrafficController.foreach( _ => throw PlateauAlreadyDefinedException("A plateau has already been set."))
    implicit val plateau = Plateau(upperX + 1, upperYt + 1)
    maybeTrafficController = Option( TrafficController() )
  }

}
