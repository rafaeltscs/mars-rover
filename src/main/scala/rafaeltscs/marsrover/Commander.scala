package rafaeltscs.marsrover

import rafaeltscs.marsrover.controller.PlateauController
import rafaeltscs.marsrover.exception.PlateauAlreadyDefinedException
import rafaeltscs.marsrover.model.Plateau

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/**
  * Singleon object that controls the rovers and the plateau.
  */
object Commander {

  private val COMMAND_PATTERN: Regex = "([aA-zZ]|[0-9])+ *([aA-zZ]|[0-9])*".r
  private var plateauController: Option[PlateauController] = None

  object COMMANDS {
    val PLATEAU_PATTERN: Regex = "Plateau:[0-9]+ [0-9]+$".r
    val LANDING: String = "Landing"
    val INSTRUCTIONS: String = "Instructions"
    val MOVE_ROVER: Char = 'M'
    val TURN_ROVER_LEFT: Char = 'L'
    val TURN_ROVER_RIGHT: Char = 'R'
    val PROCEED: String = ""
  }

  def command(order: => String, instructions : ArrayBuffer[String] = ArrayBuffer[String]()) {
    println("Waiting for Instructions. Press ENTER to proceed.")
    order match {
      case COMMANDS.PROCEED =>
        processCommands(instructions)
        println("finished.")
      case str: String =>
        instructions.+=(str)
        command(order,instructions)
    }
  }

  def processCommands(commands: ArrayBuffer[String]): Unit = {
    if(commands.isEmpty){
      println("No instructions provided. Shutting down the system...")
    }

    //TODO: fix
    commands.foreach { instrution =>
      instrution match {
        case COMMANDS.PLATEAU_PATTERN => println("plateau")
        //        val coordinates = c.split(":")(1).split(" ").map(_.toInt)
        //        initPlateauController(coordinates(0),coordinates(1))
      }
    }
  }

  private def initPlateauController(width: Int, height: Int): Unit = {
    plateauController.foreach(throw PlateauAlreadyDefinedException("A plateau has already been set."))
    plateauController = Option(PlateauController(Plateau(width,height)))
  }
}
