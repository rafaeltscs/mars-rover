package rafaeltscs.marsrover

import rafaeltscs.marsrover.controller.TrafficController
import rafaeltscs.marsrover.exception.PlateauAlreadyDefinedException
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

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
      val LANDING: Regex = "(^Rover1) (Landing):(1) (2) (N$)".r
      val INSTRUCTIONS: Regex = "Instructions".r
    }

    val MOVE_ROVER: Char = 'M'
    val TURN_ROVER_LEFT: Char = 'L'
    val TURN_ROVER_RIGHT: Char = 'R'
    val PROCEED: String = ""

    def isPlateauCommand(command: String): Boolean = {
      Patterns.PLATEAU.findFirstIn(command).isDefined
    }

    def isLandingCommand(command: String): Boolean = {
      Patterns.LANDING.findFirstIn(command).isDefined
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
      case commandStr: String =>
        var validCommand = true

        if (instructions.exists(_ => Commands.isPlateauCommand(commandStr))) {
          println(s"A plateau has already been set. Instruction '$commandStr' will be ignored.")
          validCommand = false
        }
//        else if(Commands.Patterns.LANDING.findFirstIn(commandStr).map(_.split(" ")(0)).) {
//          TODO: continue
//        }

        if (validCommand) command(order, instructions.:+(commandStr))
        else command(order, instructions)
    }
  }

  def processCommands(commands: Seq[String]): Unit = {
    if(commands.isEmpty){
      println("No instructions provided. Shutting down the system...")
    }

    commands.foreach  {
      case Commands.Patterns.PLATEAU(x,y) => println("plateau")
        initPlateauController(x.toInt,y.toInt)
    }
  }

  private def initPlateauController(width: Int, height: Int): Unit = {
    maybeTrafficController.foreach( _ => throw PlateauAlreadyDefinedException("A plateau has already been set."))
    maybeTrafficController = Option( TrafficController(Plateau(width,height)) )
  }

}
