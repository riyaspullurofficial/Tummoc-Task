package com.riyas.tummoctask.model.travelapimodel

data class SubDirectionModel(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)