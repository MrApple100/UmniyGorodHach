package com.example.umniygorodhach.data.remote.api.news.models

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemResponse(
    val id : Int,
    val author : String,
    val title : String,
    val picture : String,
    val text : String,
    val time : Int
) {
}
