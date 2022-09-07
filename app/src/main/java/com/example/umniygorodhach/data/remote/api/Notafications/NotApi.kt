package com.example.umniygorodhach.data.remote.api.Notafications

import com.example.umniygorodhach.data.remote.api.Notafications.models.Notaf
import retrofit2.http.GET

interface NotApi {

    @GET("/notafication")
    suspend fun getNotes():List<Notaf>
}