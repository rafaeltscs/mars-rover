package rafaeltscs.marsrover

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import rafaeltscs.marsrover.exception.InvalidPositionException
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}
import rafaeltscs.marsrover.types.Types.Direction

import scala.collection.mutable.ArrayBuffer

class RoverSpec extends Specification with RoversController {
  isolated

  "Rover facing NORTH should" >> {
    val rover = Rover("roverTest", Position(3, 3, Direction.NORTH))

    "turn LEFT and face WEST" >> {
      rover.move('L').toString must beEqualTo("roverTest:3 3 W")
    }

    "turn RIGHT and face EAST'" >> {
      rover.move('R').toString must beEqualTo("roverTest:3 3 E")
    }

    "move forward and keep facinf NORTH" >> {
      rover.move('M').toString must beEqualTo("roverTest:3 4 N")
    }
  }

  "Rover facing EAST should" >> {
    val rover = Rover("roverTest", Position(3, 3, Direction.EAST))

    "turn LEFT and face NORTH" >> {
      rover.move('L').toString must beEqualTo("roverTest:3 3 N")
    }

    "turn RIGHT and face SOUTH'" >> {
      rover.move('R').toString must beEqualTo("roverTest:3 3 S")
    }

    "move forward and keep facing EAST" >> {
      rover.move('M').toString must beEqualTo("roverTest:4 3 E")
    }
  }

  "Rover facing SOUTH should" >> {
    val rover = Rover("roverTest", Position(3, 3, Direction.SOUTH))

    "turn LEFT and face EAST" >> {
      rover.move('L').toString must beEqualTo("roverTest:3 3 E")
    }

    "turn RIGHT and face WEST'" >> {
      rover.move('R').toString must beEqualTo("roverTest:3 3 W")
    }

    "move forward and keep facing SOUTH" >> {
      rover.move('M').toString must beEqualTo("roverTest:3 2 S")
    }
  }

  "Rover facing WEST should" >> {
    val rover = Rover("roverTest", Position(3, 3, Direction.WEST))

    "turn LEFT and face SOUTH" >> {
      rover.move('L').toString must beEqualTo("roverTest:3 3 S")
    }

    "turn RIGHT and face NORTH'" >> {
      rover.move('R').toString must beEqualTo("roverTest:3 3 N")
    }

    "move forward and keep facing WEST" >> {
      rover.move('M').toString must beEqualTo("roverTest:2 3 W")
    }
  }

  "Rover should throw exception when" >> {

    "Trying to move to an occupied position" >> {
      val rover1 = Rover("roverTest1", Position(3, 3, Direction.NORTH))
      val rover2 = Rover("roverTest2", Position(3, 2, Direction.NORTH))

      rovers.+=(rover1, rover2)

      rover2.move('M') must throwA[InvalidPositionException]

    }

    "Trying to move to an invalid position" >> {
      val rover3 = Rover("roverTest3", Position(10, 10, Direction.NORTH))
      rovers.+=(rover3)

      rover3.move('M') must throwA[InvalidPositionException]
    }
  }

}

trait RoversController extends Scope {
  implicit val plateau : Plateau = Plateau(10,10)
  implicit val rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()
}