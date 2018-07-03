package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.types.Types
import rafaeltscs.marsrover.types.Types.Direction

case class Position(x: Int, y: Int, direction: Direction) {
  def rotateLeft : Position = {
    copy(direction = Types.Direction.rotateLeft(direction.intValue(),8))
  }

  def rotateRight : Position = {
    copy(direction = Types.Direction.rotateRight(direction.intValue(),8))
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