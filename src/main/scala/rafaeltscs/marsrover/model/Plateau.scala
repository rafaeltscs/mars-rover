package rafaeltscs.marsrover.model

import scala.collection.mutable.ArrayBuffer

case class Plateau(width: Int, height: Int) {
  val board : ArrayBuffer[ArrayBuffer[Option[String]]] = ArrayBuffer.fill(height){ArrayBuffer.fill(width){None}}
}
