package com.riyas.tummoctask.model.travelapimodel

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)