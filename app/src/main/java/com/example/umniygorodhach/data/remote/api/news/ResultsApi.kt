package com.example.umniygorodhach.data.remote.api.news

import com.example.umniygorodhach.data.remote.api.news.models.MySborkaItem
import com.example.umniygorodhach.data.remote.api.news.models.RatingRegionItem
import retrofit2.http.GET

interface ResultsApi {

    @GET("/TeAm/1")
    suspend fun getMySborka() : List<MySborkaItem>

    @GET("/regions")
    suspend fun getRatingRegions() : List<RatingRegionItem>
}