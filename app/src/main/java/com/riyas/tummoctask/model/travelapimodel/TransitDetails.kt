package com.riyas.tummoctask.model.travelapimodel

data class TransitDetails(
    val arrival_stop: ArrivalStop,
/*    val arrival_time: ArrivalTimeX,*/
    val departure_stop: DepartureStop,
   /* val departure_time: DepartureTimeX,*/
    val headsign: String,
    val line: Line,
    val num_stops: Int
)