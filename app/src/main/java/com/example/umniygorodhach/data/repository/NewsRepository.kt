package com.example.umniygorodhach.data.repository

import android.util.Log
import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.news.NewsApi
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.remote.api.news.models.TransItemResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val handler: ResponseHandler
) {

    suspend fun fetchNews() = handler{
       val list = newsApi.getNews() as MutableList<NewsItemResponse>
        list
    }
    suspend fun fetchTrans() = handler{
         val list = newsApi.getTrans() as MutableList<TransItemResponse>
        Log.d("Tag",list.toString())
        list
    }
    suspend fun fetchOnlineMatch() = handler{
        newsApi.getOnlineMatch()


    }

}