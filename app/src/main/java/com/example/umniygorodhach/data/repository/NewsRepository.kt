package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.news.NewsApi
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val handler: ResponseHandler
) {

    suspend fun fetchNews() = handler{
        newsApi.getNews() as MutableList<NewsItemResponse>
    }

}