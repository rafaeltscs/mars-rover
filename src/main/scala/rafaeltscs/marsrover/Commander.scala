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
    val PLATEAU_PATTERN = "^Plateau:([0-9]+) ([0-9]+$)".r
    val LANDING: String = "Landing"
    val INSTRUCTIONS: String = "Instructions"
    val MOVE_ROVER: Char = 'M'
    val TURN_ROVER_LEFT: Char = 'L'
    val TURN_ROVER_RIGHT: Char = 'R'
    val PROCEED: String = ""
  }

  def command(order: => String, instructions : Seq[String] = Seq[String]()) {
    println("Waiting for Instructions. Press ENTER to proceed.")
    instructions.foreach(println)
    order match {
      case COMMANDS.PROCEED =>
        processCommands(instructions)
        println("finished.")
      case str: String =>
        command(order,instructions.:+(str))
    }
  }

  def processCommands(commands: Seq[String]): Unit = {
    if(commands.isEmpty){
      println("No instructions provided. Shutting down the system...")
    }

    commands.foreach  {
      case COMMANDS.PLATEAU_PATTERN(x,y) => println("plateau")
        initPlateauController(x.toInt,y.toInt)
    }
  }

  private def initPlateauController(width: Int, height: Int): Unit = {
    plateauController.foreach( _ => throw PlateauAlreadyDefinedException("A plateau has already been set."))
    plateauController = Option(PlateauController(Plateau(width,height)))
  }
}
