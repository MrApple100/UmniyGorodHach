package com.example.umniygorodhach.data.remote.api.news.models

import kotlinx.serialization.Serializable

@Serializable
data class OnlineMatch(
    val firstteam:String,
    val secondteam:String,
    val firstNumber:String,
    val secondNumber:String,
    val vid:String,
    val term:String,
    val url:String
)
