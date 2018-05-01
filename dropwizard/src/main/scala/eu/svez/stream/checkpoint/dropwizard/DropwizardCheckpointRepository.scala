package eu.svez.stream.checkpoint.dropwizard

import java.util.concurrent.TimeUnit.NANOSECONDS

import com.codahale.metrics.MetricRegistry
import eu.svez.stream.checkpoint.CheckpointRepository

private[checkpoint] object DropwizardCheckpointRepository {

  def apply(name: String)(implicit metricRegistry: MetricRegistry): CheckpointRepository = new CheckpointRepository {

    private val pullTimer = metricRegistry.timer(name + "_pull")
    private val pushTimer = metricRegistry.timer(name + "_push")

    override def addPullLatency(nanos: Long): Unit = pullTimer.update(nanos, NANOSECONDS)
    override def addPushLatency(nanos: Long): Unit = pushTimer.update(nanos, NANOSECONDS)
  }
}