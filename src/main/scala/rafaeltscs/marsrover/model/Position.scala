package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.types.Types.Direction

case class Position(x: Int, y: Int, direction: Direction) {

  def moveForward: Position = {
    direction match {
      case Position.DIRECTION.NORTH => moveNorth
      case Position.DIRECTION.EAST => moveEast
      case Position.DIRECTION.SOUTH => moveSouth
      case Position.DIRECTION.WEST => moveWest
    }
  }

  def rotateLeft : Position = {
    copy(direction = Integer.rotateLeft(direction.intValue(),8))
  }

  def rotateRight : Position = {
    copy(direction = Integer.rotateRight(direction.intValue(),8))
  }

  private def moveNorth: Position = {
    copy(y = y+1)
  }

  private def moveSouth: Position = {
    copy(y = y-1)
  }

  private def moveEast: Position = {
    copy(y = x+1)
  }

  private def moveWest: Position = {
    copy(y = x-1)
  }

}

object Position {
  object DIRECTION {
    val NORTH: Direction = 1         // 1
    val EAST: Direction  = 256       // 100000000
    val SOUTH: Direction = 65536     // 10000000000000000
    val WEST: Direction  = 16777216  // 1000000000000000000000000
  }
}