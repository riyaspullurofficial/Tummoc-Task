package com.riyas.tummoctask.network

import com.riyas.tummoctask.model.travelapimodel.SubDirectionModel
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {


    suspend fun getDirections(): SubDirectionModel = apiService.getDirections()


}