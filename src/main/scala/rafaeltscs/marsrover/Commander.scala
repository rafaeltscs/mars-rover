package rafaeltscs.marsrover

import scala.collection.mutable.ArrayBuffer

/**
  * Singleon object that controls the rovers and the plateau.
  */
object Commander {

  object COMMANDS {
    val PLATEAU: String = "Plateau"
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
    commands.foreach(println)
  }
}
