package rafaeltscs.marsrover

import org.specs2.mutable.Specification
import rafaeltscs.marsrover.model.Position
import rafaeltscs.marsrover.types.Types

class PositionSpec extends Specification {
  "Position facing NORTH should" >> {

    val position = Position(3, 3, Types.Direction.NORTH)

    "rotate LEFT and face WEST'" >> {
      position.rotateLeft.toString must beEqualTo("3 3 W")
    }

    "rotate RIGHT and face EAST" >> {
      position.rotateRight.toString must beEqualTo("3 3 E")
    }

    "move forward and keep facing NORTH'" >> {
      position.moveForward.toString must beEqualTo("3 4 N")
    }
  }

  "Position facing EAST should" >> {

    val position = Position(3, 3, Types.Direction.EAST)

    "rotate LEFT and face NORTH" >> {
      position.rotateLeft.toString must beEqualTo("3 3 N")
    }

    "rotate RIGHT and face SOUTH'" >> {
      position.rotateRight.toString must beEqualTo("3 3 S")
    }

    "move forward and keep facing EAST" >> {
      position.moveForward.toString must beEqualTo("4 3 E")
    }
  }

  "Position facing SOUTH should" >> {

    val position = Position(3, 3, Types.Direction.SOUTH)

    "rotate LEFT and face EAST" >> {
      position.rotateLeft.toString must beEqualTo("3 3 E")
    }

    "rotate RIGHT and face WEST'" >> {
      position.rotateRight.toString must beEqualTo("3 3 W")
    }

    "move forward and keep facing SOUTH" >> {
      position.moveForward.toString must beEqualTo("3 2 S")
    }
  }

  "Position facing WEST should" >> {

    val position = Position(3, 3, Types.Direction.WEST)

    "rotate LEFT and face SOUTH" >> {
      position.rotateLeft.toString must beEqualTo("3 3 S")
    }

    "rotate RIGHT and face NORTH'" >> {
      position.rotateRight.toString must beEqualTo("3 3 N")
    }

    "move forward and keep facing WEST" >> {
      position.moveForward.toString must beEqualTo("2 3 W")
    }
  }
}
