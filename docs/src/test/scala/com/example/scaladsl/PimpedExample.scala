package com.example.scaladsl

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.codahale.metrics.MetricRegistry

object PimpedExample extends App {
  implicit val system       = ActorSystem.create("stream-checkpoint-java-demo")
  implicit val materializer = ActorMaterializer.create(system)
  import system.dispatcher

  implicit val metricRegistry = new MetricRegistry()

  // #pimped
  import akka.stream.checkpoint.DropwizardBackend._
  import akka.stream.checkpoint.scaladsl.Implicits._

  Source(1 to 100)
    .checkpoint("produced")
    .filter((x: Int) ⇒ x % 2 == 0)
    .checkpoint("filtered")
    .runForeach(println)
  // #pimped
    .onComplete { _ ⇒  system.terminate() }
}
