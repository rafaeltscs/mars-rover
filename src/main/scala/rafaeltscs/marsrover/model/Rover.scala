package rafaeltscs.marsrover.model

case class Rover(name: String, position: Position, plateau: Plateau) {

  def move(command: String): Unit = {

  }

  private def moveForward: Unit = {

  }

  private def turnLeft: Rover = {
    copy(position = position.rotateLeft)
  }

  private def turnRight: Rover = {
    copy(position = position.rotateRight)
  }

}
