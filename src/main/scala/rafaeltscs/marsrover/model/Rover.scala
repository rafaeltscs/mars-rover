package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.exception.InvalidPositionException
import rafaeltscs.marsrover.types.Types.Command

import scala.collection.mutable.ArrayBuffer


case class Rover(name: String, position: Position)(implicit plateau: Plateau) {

  def move(command: Char)(implicit rovers: ArrayBuffer[Rover]): Rover = {
    command match {
      case Command.Instructions.MOVE_ROVER => validating(moveForward)
      case Command.Instructions.TURN_ROVER_LEFT => turnLeft
      case Command.Instructions.TURN_ROVER_RIGHT => turnRight
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

  private def validating[T](f: => T)(implicit rovers: ArrayBuffer[Rover]): T = {
    val result : T = f
    val resultRorver: Rover = result.asInstanceOf[Rover]
    val resultPosition: Position = resultRorver.position

    plateau.validatePosition(resultPosition)

    if(rovers.exists(_.position.same(resultPosition)))
      throw InvalidPositionException(s"The position (${resultPosition.toString}) is occupied. Stopped at (${position.toString})")

    result
  }

}
