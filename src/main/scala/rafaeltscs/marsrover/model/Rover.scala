package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.Commander


case class Rover(name: String, position: Position, plateau: Plateau) {

  def move(command: Char): Rover = {
    command match {
      case Commander.Commands.MOVE_ROVER => moveForward
      case Commander.Commands.TURN_ROVER_LEFT => turnLeft
      case Commander.Commands.TURN_ROVER_RIGHT => turnRight
    }
  }

  override def toString: String = {
    s"$name:$position"
  }

  private def moveForward: Rover = {
    copy(position = position.moveForward)
  }

  private def turnLeft: Rover = {
    copy(position = position.rotateLeft)
  }

  private def turnRight: Rover = {
    copy(position = position.rotateRight)
  }

}
