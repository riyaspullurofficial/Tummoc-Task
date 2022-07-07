package com.riyas.tummoctask.model.travelapimodel

data class Step(
    val distance: Distance,
    val html_instructions: String,
    val polyline: Polyline,

    val steps: List<StepX>,
    val transit_details: TransitDetails,
    val travel_mode: String
)