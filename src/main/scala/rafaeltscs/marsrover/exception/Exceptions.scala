package rafaeltscs.marsrover.exception

case class InvalidPositionException(msg: String) extends RuntimeException(msg)
case class CannotMoveException(msg: String, cause: Option[Throwable] = None) extends RuntimeException(msg) {
  cause.foreach(initCause)
}