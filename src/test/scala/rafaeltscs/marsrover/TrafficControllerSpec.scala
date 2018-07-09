package rafaeltscs.marsrover

import org.specs2.mutable.Specification
import rafaeltscs.marsrover.controller.TrafficController
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}
import rafaeltscs.marsrover.types.Types.Direction

import scala.collection.mutable.ArrayBuffer

class TrafficControllerSpec extends Specification {

  "TrafficController should" >> {
    implicit val plateau: Plateau = Plateau(10,10)
    val controller = TrafficController()

    "move a rover through instructions" >> {
      val rover1 = Rover("Rover1", Position(1,2,Direction.NORTH))
      val rover2 = Rover("Rover2", Position(3,3,Direction.EAST))
      implicit val rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()

      "LMLMLMLMM".foldLeft[Rover](rover1){ (r,i) => r.move(i)}.toString must beEqualTo("Rover1:1 3 N")
      "MMRMMRMRRM".foldLeft[Rover](rover2){ (r,i) => r.move(i)}.toString must beEqualTo("Rover2:5 1 E")

    }
  }
}
