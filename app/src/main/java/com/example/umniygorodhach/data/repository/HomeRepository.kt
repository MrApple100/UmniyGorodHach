package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.home.HomeApi
import javax.inject.Inject


class HomeRepository @Inject constructor(
    private val eventsApi: HomeApi,
    private val handler: ResponseHandler
) {

}