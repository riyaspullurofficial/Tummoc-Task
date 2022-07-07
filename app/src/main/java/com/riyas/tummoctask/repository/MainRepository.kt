package com.riyas.tummoctask.repository

import com.riyas.tummoctask.model.travelapimodel.SubDirectionModel
import com.riyas.tummoctask.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {


    fun getDirections(): Flow<SubDirectionModel> = flow {
        emit(apiServiceImpl.getDirections())
    }.flowOn(Dispatchers.IO)



}