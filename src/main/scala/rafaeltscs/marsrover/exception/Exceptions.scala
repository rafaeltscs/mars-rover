package rafaeltscs.marsrover.exception

case class DuplicateRoverException(msg: String) extends RuntimeException(msg)
case class NoRoverException(msg: String) extends RuntimeException(msg)
case class InvalidPositionException(msg: String) extends RuntimeException(msg)
case class CannotMoveException(msg: String, cause: Option[Throwable] = None) extends RuntimeException(msg) {
  cause.foreach(initCause)
}

case class PlateauAlreadyDefinedException(msg: String) extends RuntimeException(msg)
