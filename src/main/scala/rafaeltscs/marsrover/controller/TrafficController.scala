package rafaeltscs.marsrover.controller

import rafaeltscs.marsrover.exception._
import rafaeltscs.marsrover.model.{Plateau, Position, Rover}

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success, Try}

case class TrafficController(implicit plateau: Plateau, rovers: ArrayBuffer[Rover] = ArrayBuffer[Rover]()) {

  def moveRover(name: String, instructions: String): Rover = {
    Try {
      val rover: Rover = rovers.find(_.name == name)
        .getOrElse(throw NoRoverException(s"No rover was found with the name $name"))

      instructions.foldLeft[Rover](rover){ (r,i) =>
        val resultRover: Rover = r.move(i)
        rovers -= r
        rovers += resultRover
        resultRover
      }
    } match {
      case Success(r) => r
      case Failure(e) => throw CannotMoveROverException(s"Could not move rover '$name", Option(e))
    }
  }

  def landRover(name: String, position: Position): Rover = {
    validatingLanging(name, position) {
      val landingRover = Rover(name, position)
      rovers.+=(landingRover)
      landingRover
    }
  }

  def report: String = {
    rovers.mkString("\n")
  }

  private def validatingLanging[T](name: String, position: Position)(f: => T): T = {
    Try {
      plateau.validatePosition(position)

      if (rovers.map(_.name.toLowerCase).contains(name.toLowerCase))
        throw DuplicateRoverException(s"A rover with the name '$name' already exists")

      if (rovers.exists(_.position.same(position)))
        throw InvalidPositionException(s"The position (${position.toString}) is occupied")

    } match {
      case Success(_) => f
      case Failure(cause) => throw CouldNotLandRoverException(s"Could not land the rover '$name", Option(cause))
    }
  }

}
