# Backends
Every checkpoint needs a backend to store metrics readings.

## Dropwizard
The [Dropwizard](http://metrics.dropwizard.io) backend creates the following metrics for each checkpoint. Note that each metric will be prefixed
with the checkpoint label you specify.

* `{label}_push_latency`: @extref[histogram](dw-docs:/#histograms) to store the latencies between push and pull signals in nanoseconds
* `{label}_pull_latency`: @extref[histogram](dw-docs:/#histograms) to store the latencies between pull and push signals in nanoseconds
* `{label}_throughput`: @extref[meter](dw-docs://#meters) to store the throughput rate of the checkpoint
* `{label}_backpressure_ratio`: @extref[histogram](dw-docs:/#histograms) to store the percentage of time the checkpoint is backpressuring
* `{label}_backpressured`: @extref[counter](dw-docs://#counters) to store the current backpressure state of the checkpoint

## Kamon
The [Kamon](https://kamon.io) backend creates the following metrics for each checkpoint. Note that each metric will be prefixed
with the checkpoint label you specify.

* `{label}_push_latency`: @extref[histogram](kamon-docs:/core/basics/metrics/) to store the latencies between push and pull signals in nanoseconds
* `{label}_pull_latency`: @extref[histogram](kamon-docs:/core/basics/metrics/) to store the latencies between pull and push signals in nanoseconds
* `{label}_throughput`: @extref[counter](kamon-docs:/core/basics/metrics/) to store the throughput rate of the checkpoint
* `{label}_backpressure_ratio`: @extref[histogram](kamon-docs:/core/basics/metrics/) to store the percentage of time the checkpoint is backpressuring
* `{label}_backpressured`: @extref[gauge](kamon-docs:/core/basics/metrics/) to store the current backpressure state of the checkpoint

## Custom

Custom backends can be created by implementing `CheckpointBackend` and `CheckpointRepository` interfaces. The following
example is a backend that simply prints readings to stdout.

Scala
: @@ snip [dummy](../../test/scala/com/example/scaladsl/CustomBackendExample.scala) { #custom }

Java
: @@ snip [dummy](../../test/java/com/example/javadsl/CustomBackendExample.java) { #custom }
