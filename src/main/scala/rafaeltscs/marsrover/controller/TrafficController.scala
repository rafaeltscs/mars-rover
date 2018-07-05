package rafaeltscs.marsrover.controller

import rafaeltscs.marsrover.exception.{DuplicateRoverException, InvalidPositionException}
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

import scala.collection.mutable.ArrayBuffer

case class TrafficController(plateau: Plateau, rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()) {

  def landRover(name: String, position: Position): Unit = validatingMove(position) {
    if(rovers.map(_.name.toLowerCase).contains(name.toLowerCase))
      throw DuplicateRoverException(s"A rover with the name '$name' already exists." )

    rovers.+=(Rover(name,position,plateau))
  }

  def report: String = {
    rovers.mkString("\n")
  }

  private def validatingMove[T](position: Position)(f: => T): T = {
    plateau.validatesPosition(position)

    if(rovers.exists(_.position.same(position)))
      throw InvalidPositionException(s"The position (${position.toString}) is occupied.")

    f
  }

}
