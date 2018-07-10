package rafaeltscs.marsrover.types

import rafaeltscs.marsrover.types.Types.Command.Patterns

object Types {
  /* Direction Type ***************************/
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
    val WEST: Direction  = 256       // 100000000
    val SOUTH: Direction = 65536     // 10000000000000000
    val EAST: Direction  = 16777216  // 1000000000000000000000000

    def fromChar(char: Char): Direction = {
      char match {
        case 'N' => NORTH
        case 'E' => EAST
        case 'S' => SOUTH
        case 'W' => WEST
      }
    }
  }

  /* Command Type ****************************************/
  type Command = String

  implicit class CommandExtension(c: Command) {
    def isPlateauCommand: Boolean = {
      Patterns.PLATEAU.findFirstIn(c).isDefined
    }
  }

  object Command {
    import scala.util.matching.Regex

    object Patterns {
      val PLATEAU: Regex = "^Plateau:([0-9]+) ([0-9]+$)".r
      val LANDING: Regex = "^(\\w+) Landing:([0-9]+) ([0-9]+) ([NSEW]$)".r
      val INSTRUCTIONS: Regex = "^(\\w+) Instructions:([MLR]+$)".r
    }

    object Instructions {
      val MOVE_ROVER: Char = 'M'
      val TURN_ROVER_LEFT: Char = 'L'
      val TURN_ROVER_RIGHT: Char = 'R'
      val PROCEED: String = ""
    }

  }
}
