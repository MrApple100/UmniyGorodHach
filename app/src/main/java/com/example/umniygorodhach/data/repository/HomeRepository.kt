package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.home.HomeApi
import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import javax.inject.Inject


class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
    private val handler: ResponseHandler
) {
    suspend fun fetchTimeTable() = handler{
        homeApi.getTimeTabel(1) as MutableList<RaspItem>
    }
}