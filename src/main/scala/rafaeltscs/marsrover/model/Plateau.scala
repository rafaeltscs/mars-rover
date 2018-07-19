package rafaeltscs.marsrover.model

import rafaeltscs.marsrover.exception.InvalidPositionException

case class Plateau(width: Int, height: Int) {
  def validatePosition(position: Position): Unit = {
    if(position.x < 0 || position.x >= width || position.y < 0 || position.y >= height)
      throw InvalidPositionException(s"Invalid position: (${position.x} ${position.y}). It must be from (0 0) to (${width - 1} ${height - 1})")
  }
}
