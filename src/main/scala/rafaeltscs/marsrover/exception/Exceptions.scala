package rafaeltscs.marsrover.exception

case class DuplicateRoverException(msg: String) extends Throwable(msg)
case class NoRoverException(msg: String) extends Throwable(msg)
case class InvalidPositionException(msg: String) extends Throwable(msg)
case class CannotMoveROverException(msg: String, cause: Option[Throwable] = None) extends Throwable(msg) {
  cause.foreach(initCause)
}

case class CouldNotLandRoverException(msg: String, cause: Option[Throwable] = None) extends Throwable(msg) {
  cause.foreach(initCause)
}

case class PlateauAlreadyDefinedException(msg: String) extends Throwable(msg)
