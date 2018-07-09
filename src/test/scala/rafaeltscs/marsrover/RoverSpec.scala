package rafaeltscs.marsrover

import org.specs2.mutable.Specification
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}
import rafaeltscs.marsrover.types.Types.Direction

import scala.collection.mutable.ArrayBuffer

class RoverSpec extends Specification {
  "Rover '3 3 N' should" >> {

    implicit val plateau : Plateau = Plateau(10,10)
    implicit val rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()
    val rover = Rover("roverTest", Position(3, 3, Direction.NORTH))

    "turn left and become 'roverTest:3 3 W'" >> {
      rover.move('L').toString must beEqualTo("roverTest:3 3 W")
    }

    "turn right and become 'roverTest:3 3 E'" >> {
      rover.move('R').toString must beEqualTo("roverTest:3 3 E")
    }

    "move forward and become 'roverTest:3 4 N'" >> {
      rover.move('M').toString must beEqualTo("roverTest:3 4 N")
    }

  }
}
