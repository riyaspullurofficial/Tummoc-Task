package com.riyas.tummoctask.model.travelapimodel

data class Line(
    val agencies: List<Agency>,
    val color: String,
    val name: String,
    val short_name: String,
    val text_color: String,
    val vehicle: Vehicle
)