package rafaeltscs.marsrover

/**
  * Singleon object that controls the rovers and the plateau.
  */
object Commander {

  object COMMANDS {
    lazy val PLATEAU: String = "Plateau"
    lazy val LANDING: String = "Landing"
    lazy val INSTRUCTIONS: String = "Instructions"
    lazy val MOVE_ROVER: Char = 'M'
    lazy val TURN_ROVER_LEFT: Char = 'L'
    lazy val TURN_ROVER_RIGHT: Char = 'R'
  }
}
