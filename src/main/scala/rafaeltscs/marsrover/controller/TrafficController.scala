package rafaeltscs.marsrover.controller

import rafaeltscs.marsrover.exception.{DuplicateRoverException, InvalidPositionException, NoRoverException}
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

import scala.collection.mutable.ArrayBuffer

case class TrafficController(implicit plateau: Plateau, rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()) {

  def moveRover(name: String, instructions: String): Unit = {
    val rover: Rover = rovers.find(_.name == name)
                              .getOrElse(throw NoRoverException(s"No rover was found with the name $name"))

    instructions.foreach{ i =>
      rovers.diff(Array(rover)).+:(rover.move(i))
    }
  }

  def landRover(name: String, position: Position): Unit = validateLanging(position) {
    if(rovers.map(_.name.toLowerCase).contains(name.toLowerCase))
      throw DuplicateRoverException(s"A rover with the name '$name' already exists." )

    rovers.+=(Rover(name,position))
  }

  def report: String = {
    rovers.mkString("\n")
  }

  private def validateLanging[T](position: Position)(f: => T): T = {
    plateau.validatePosition(position)

    if(rovers.exists(_.position.same(position)))
      throw InvalidPositionException(s"The position (${position.toString}) is occupied.")

    f
  }

}
