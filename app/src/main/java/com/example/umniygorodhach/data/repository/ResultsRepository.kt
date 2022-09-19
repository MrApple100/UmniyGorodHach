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
    //Mock Data
    val mySborkaItem = MySborkaItem(0,0,"Вектор","1/4","14")
    val mySborkaItem2 = MySborkaItem(1,0,"Диод","1/2","5")
    val mySborkaItem3 = MySborkaItem(2,0,"Reality Cringe","Финал","1")

    val listsborka = arrayListOf(mySborkaItem,mySborkaItem2,mySborkaItem3)


    suspend fun fetchSborka() = handler{
        //resultsApi.getMySborka() as MutableList<MySborkaItem>
        listsborka
    }
    val rating1 = RatingRegionItem("1","Иркутская обл.","100")
    val rating2 = RatingRegionItem("2","Московская обл.","97")
    val rating3 = RatingRegionItem("3","Ленинградская обл.","90")
    val rating4 = RatingRegionItem("4","Приморский край","85")

    val listrating = arrayListOf(rating1,rating2,rating3,rating4)

    suspend fun fetchRatingRegion() = handler{
        //resultsApi.getRatingRegions() as MutableList<RatingRegionItem>
        listrating
    }


}