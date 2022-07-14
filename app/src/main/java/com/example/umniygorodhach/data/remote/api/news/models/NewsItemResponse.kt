package com.example.umniygorodhach.data.remote.api.news.models

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemResponse(
    val id : String,
    val author : String,
    val title : String,
    val pictureUrl : String,
    val description : String,
    val date : String
) {
}
