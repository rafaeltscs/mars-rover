package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.Commander


case class Rover(name: String, position: Position, plateau: Plateau) {

  def move[T](command: Char)(validating: (Plateau, Position) => T): Rover = {
    command match {
      case Commander.Commands.MOVE_ROVER => moveForward(validating)
      case Commander.Commands.TURN_ROVER_LEFT => turnLeft
      case Commander.Commands.TURN_ROVER_RIGHT => turnRight
    }
  }

  override def toString: String = {
    s"$name:$position"
  }

  private def moveForward[T](validating: (Plateau,Position) => T): Rover = {
    val newPosition = position.moveForward
    validating(plateau,newPosition) {
      copy(position = position.moveForward)
    }
  }

  private def turnLeft: Rover = {
    copy(position = position.rotateLeft)
  }

  private def turnRight: Rover = {
    copy(position = position.rotateRight)
  }

}
