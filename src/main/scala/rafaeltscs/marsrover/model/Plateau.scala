package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.exception.InvalidPositionException

import scala.collection.mutable.ArrayBuffer

case class Plateau(width: Int, height: Int, rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()) {
  def validatesPosition(position: Position): Unit = {
    if(position.x >= width || position.y >= height)
      throw InvalidPositionException(s"Invalid position: (${position.x} ${position.y}). It must be from (0 0) to (${width - 1} ${height - 1})")
  }
}
