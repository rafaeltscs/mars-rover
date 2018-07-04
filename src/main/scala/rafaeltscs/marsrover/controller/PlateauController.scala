package rafaeltscs.marsrover.controller

import rafaeltscs.marsrover.exception.InvalidPositionException
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

import scala.collection.mutable.ArrayBuffer

case class PlateauController(plateau: Plateau, rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()) {

  def landRover(name: String, position: Position) = {



  }

}
