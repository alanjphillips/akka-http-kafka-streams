package com.alaphi.httptokafka


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory


object Boot extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val config = ConfigFactory.load()

  val routes = Routes().workerRoutes

  val bindingFuture = Http().bindAndHandle(routes, "0.0.0.0", 8081)

}
