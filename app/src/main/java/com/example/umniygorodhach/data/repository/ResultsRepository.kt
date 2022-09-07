package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.news.NewsApi
import com.example.umniygorodhach.data.remote.api.news.ResultsApi
import com.example.umniygorodhach.data.remote.api.news.models.MySborkaItem
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.remote.api.news.models.RatingRegionItem
import javax.inject.Inject

class ResultsRepository @Inject constructor(
    private val resultsApi: ResultsApi,
    private val handler: ResponseHandler
) {

    suspend fun fetchSborka() = handler{
        resultsApi.getMySborka() as MutableList<MySborkaItem>
    }

    suspend fun fetchRatingRegion() = handler{
        resultsApi.getRatingRegions() as MutableList<RatingRegionItem>
    }


}