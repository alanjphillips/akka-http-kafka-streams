package com.alaphi.httptokafka

import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future
import scala.util.{Failure, Success}
import akka.http.scaladsl.model.StatusCodes._
import de.heikoseeberger.akkahttpcirce.CirceSupport._
import io.circe.generic.auto._

case class Sent(details: String)

class Routes (producer: KafkaProducer) {

  val workerRoutes = {
    path("service" / "work") {
      post {
        entity(as[SomethingToDo]) { somethingToDo =>
          val sent: Future[Sent] = producer.sendToKafka(somethingToDo.name)
          onComplete(sent) {
            case Success(s) => complete(s.details)
            case Failure(f) => complete(BadRequest -> "Failed")
          }
        }
      }
    }
  }

}

object Routes {
  def apply(producer: KafkaProducer): Routes = new Routes(producer)
}