package com.example.umniygorodhach.data.remote.api.home

import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApi {

    @GET("/TimeTabel/{id}")
    suspend fun getTimeTabel(@Path("id")id:Int):List<RaspItem>
}