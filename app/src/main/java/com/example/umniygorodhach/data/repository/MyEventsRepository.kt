package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.events.EventsApi
import com.example.umniygorodhach.data.remote.api.myevents.MyEventsApi
import javax.inject.Inject

class MyEventsRepository @Inject constructor(
    private val myeventsApi: MyEventsApi,
    private val handler: ResponseHandler,
) {

}