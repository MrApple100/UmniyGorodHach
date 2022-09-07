package com.example.umniygorodhach.data.remote.api.news.models

import kotlinx.serialization.Serializable

@Serializable
data class RatingRegionItem(
    val place:String,
    val region:String,
    val points:String
)
