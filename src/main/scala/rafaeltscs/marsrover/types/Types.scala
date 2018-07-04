package rafaeltscs.marsrover.types

object Types {
  type Direction = Integer

  implicit class DirectionExtension(d: Direction) {

    def rotateLeft: Direction = {
      Integer.rotateLeft(d.intValue(),8)
    }

    def rotateRight: Direction = {
      Integer.rotateRight(d.intValue(),8)
    }

    def getChar = {
      d match {
        case Direction.NORTH => 'N'
        case Direction.EAST => 'E'
        case Direction.SOUTH => 'S'
        case Direction.WEST => 'W'
      }
    }

    override def toString: String = {
      d match {
        case Direction.NORTH => "North"
        case Direction.EAST => "East"
        case Direction.SOUTH => "South"
        case Direction.WEST => "West"
      }
    }
  }

  object Direction {
    val NORTH: Direction = 1         // 1
    val EAST: Direction  = 256       // 100000000
    val SOUTH: Direction = 65536     // 10000000000000000
    val WEST: Direction  = 16777216  // 1000000000000000000000000
  }
}
