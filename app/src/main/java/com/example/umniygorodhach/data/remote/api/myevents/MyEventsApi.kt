package com.example.umniygorodhach.data.remote.api.myevents

import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import retrofit2.http.GET

interface MyEventsApi {

    @GET("MyEvent")
    suspend fun getMyEvents() : EventDetailDto

}