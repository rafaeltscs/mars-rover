package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.exception.InvalidPositionException

import scala.collection.mutable

case class Plateau(width: Int, height: Int) {
  val board : mutable.ArraySeq[mutable.ArraySeq[Option[String]]] = mutable.ArraySeq.fill(height){mutable.ArraySeq.fill(width){None}}

  def validatesPosition(position: Position): Unit = {
    if(position.x >= width || position.y >= height)
      throw InvalidPositionException(s"Invalid position: (${position.x} ${position.y}). It must be from (0 0) to (${width - 1} ${height - 1})")

    if(board(position.y)(position.x).isDefined)
      throw InvalidPositionException(s"The position (${position.toString}) is occupied.")
  }
}
