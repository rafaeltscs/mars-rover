package rafaeltscs.marsrover.controller

import rafaeltscs.marsrover.exception.{DuplicateRoverException, InvalidPositionException, NoRoverException}
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

import scala.collection.mutable.ArrayBuffer

case class TrafficController(plateau: Plateau, rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()) {

  def moveRover(name: String, instructions: String): Unit = {
    val rover: Rover = rovers.find(_.name == name)
                              .getOrElse(throw NoRoverException(s"No rover was found with the name $name"))

    instructions.foreach{ i =>
      rovers.diff(Array(rover)).+:(rover.move(i)(validateMove))
    }
  }

  def landRover(name: String, position: Position): Unit = validatingMove(plateau, position) {
    if(rovers.map(_.name.toLowerCase).contains(name.toLowerCase))
      throw DuplicateRoverException(s"A rover with the name '$name' already exists." )

    rovers.+=(Rover(name,position,plateau))
  }

  def report: String = {
    rovers.mkString("\n")
  }

  private def validatingMove[T](plateau: Plateau, position: Position)(f: => T): T = {
    plateau.validatesPosition(position)

    if(rovers.exists(_.position.same(position)))
      throw InvalidPositionException(s"The position (${position.toString}) is occupied.")

    f
  }

  private val validateMove = (plateau: Plateau, position: Position) => validatingMove(plateau, position)

}
