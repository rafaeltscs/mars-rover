package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.types.Types
import rafaeltscs.marsrover.types.Types.Direction
import rafaeltscs.marsrover.types.Types.DirectionExtension

case class Position(x: Int, y: Int, direction: Direction) {

  def moveForward: Position = {
    direction match {
      case Types.Direction.NORTH => moveNorth
      case Types.Direction.EAST => moveEast
      case Types.Direction.SOUTH => moveSouth
      case Types.Direction.WEST => moveWest
    }
  }

  def rotateLeft : Position = {
    copy(direction = direction.rotateLeft)
  }

  def rotateRight : Position = {
    copy(direction = direction.rotateRight)
  }

  private def moveNorth: Position = {
    copy(y = y+1)
  }

  private def moveSouth: Position = {
    copy(y = y-1)
  }

  private def moveEast: Position = {
    copy(x = x+1)
  }

  private def moveWest: Position = {
    copy(x = x-1)
  }

  override def toString: String = s"$x $y ${direction.getChar}"

}