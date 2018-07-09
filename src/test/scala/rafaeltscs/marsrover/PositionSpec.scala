package rafaeltscs.marsrover

import org.specs2.mutable.Specification
import rafaeltscs.marsrover.model.Position
import rafaeltscs.marsrover.types.Types

class PositionSpec extends Specification {
  "Position '3 3 N' should" >> {

    val position = Position(3, 3, Types.Direction.NORTH)

    "turn left and become '3 3 W'" >> {
      position.rotateLeft.toString must beEqualTo("3 3 W")
    }

    "turn right and become '3 3 E'" >> {
      position.rotateRight.toString must beEqualTo("3 3 E")
    }

    "move forward and become '3 4 N'" >> {
      position.moveForward.toString must beEqualTo("3 4 N")
    }
  }
}
