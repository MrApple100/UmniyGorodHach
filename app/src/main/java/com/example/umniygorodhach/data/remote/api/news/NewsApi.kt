package com.example.umniygorodhach.data.remote.api.news

import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import retrofit2.http.GET

interface NewsApi {

    @GET
    suspend fun getNews() : List<NewsItemResponse>
}