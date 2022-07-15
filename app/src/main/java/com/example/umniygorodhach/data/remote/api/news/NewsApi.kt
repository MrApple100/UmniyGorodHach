package com.example.umniygorodhach.data.remote.api.news

import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.remote.api.news.models.OnlineMatch
import com.example.umniygorodhach.data.remote.api.news.models.TransItemResponse
import retrofit2.http.GET

interface NewsApi {

    @GET("/news")
    suspend fun getNews() : List<NewsItemResponse>

    @GET("/broadcast")
    suspend fun getTrans() : List<TransItemResponse>

    @GET("/scoreboard")
    suspend fun getOnlineMatch() : OnlineMatch

}