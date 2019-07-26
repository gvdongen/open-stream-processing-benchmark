package common.benchmark.stages

/**
  * Each benchmark tech stack should have an implementation
  * of the following methods,
  * but the type signatures in the different implemenations
  * differ too much to use actual inheritance for enforcing this.
  */
trait AnalyticsStagesTemplate {


  /**
    * Aggregates over the lanes and times computation time
    *
    * - Takes a window of the long look-back period.
    * - Creates a reporter to time how long the aggregation took.
    * - Aggregates lanes of one measurement id at one point in time.
    *
    * @param parsedAndJoinedStream a [[common.benchmark.FlowObservation]] and [[common.benchmark.SpeedObservation]] belonging to a measurement point
    */
  //def generateAggregatedStream


  /**
    * Computes the relative change of the current observation compared to previous ones.
    *
    * - Creates a reporter to time how long the windowing took.
    * - Computes the relative change over time in the window.
    *
    * @param aggregatedStream of [[common.benchmark.AggregatableObservation]]
    */
  //def generateRelativeChangeStream


  /**
    * Aggregates lanes belonging to same measurement ID and timestamp.
    *
    * - Takes the average of the speed over all the lanes.
    * - Sums up the flow over all the lanes.
    * - Appends the maps and lists of timestamps and lanes.
    *
    * @param parsedAndJoinedStream [[common.benchmark.FlowObservation]] and [[common.benchmark.SpeedObservation]] for one lane for a measurement ID
    */
  //def aggregateOverLanes

    /**
    * Computes the relative change of a certain measurement ID over time.
    *
    * Short and long term relative change is computed for flow and speed
    * [obs(t) - obs(t-n)]/obs(t-n)
    *
    * @param aggregatedStream with [[common.benchmark.AggregatableObservation]] per measurement ID for the entire long look-back window
    */
  //def computeRelativeChange
}