package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.Commander


case class Rover(name: String, position: Position, plateau: Plateau) {

  def move(command: Char): Rover = {
    command match {
      case Commander.COMMANDS.MOVE_ROVER => moveForward
      case Commander.COMMANDS.TURN_ROVER_LEFT => turnLeft
      case Commander.COMMANDS.TURN_ROVER_RIGHT => turnRight
    }
  }

  private def moveForward: Rover = {
    val newPosition = position.moveForward
    plateau.validatesPosition(newPosition)

    copy(position = newPosition)
  }

  private def turnLeft: Rover = {
    copy(position = position.rotateLeft)
  }

  private def turnRight: Rover = {
    copy(position = position.rotateRight)
  }

}
