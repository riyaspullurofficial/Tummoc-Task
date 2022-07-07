package com.riyas.tummoctask.network

import com.riyas.tummoctask.model.travelapimodel.SubDirectionModel
import retrofit2.http.GET

interface ApiService {

    @GET("v3/4e03e6de-5391-4df2-b97a-d844cd412c28")
    suspend fun getDirections(): SubDirectionModel
}