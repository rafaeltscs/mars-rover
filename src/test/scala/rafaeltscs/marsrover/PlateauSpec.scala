package rafaeltscs.marsrover

import org.specs2.mutable.Specification
import rafaeltscs.marsrover.exception.InvalidPositionException
import rafaeltscs.marsrover.model.{Plateau, Position}
import rafaeltscs.marsrover.types.Types.Direction

class PlateauSpec extends Specification {

  "Plateau should throw exception when" >> {
    "Validating an invalid position" >> {
      val plateau: Plateau = Plateau(5,5)
      plateau.validatePosition(Position(6,6,Direction.NORTH)) must throwA[InvalidPositionException]
    }
  }

}