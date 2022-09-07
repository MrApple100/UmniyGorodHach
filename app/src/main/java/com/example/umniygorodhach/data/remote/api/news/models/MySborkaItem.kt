package com.example.umniygorodhach.data.remote.api.news.models

import kotlinx.serialization.Serializable

@Serializable
data class MySborkaItem(
    val id:Int,
    val idUser:Int,
    val nameteam: String,
    val term: String,
    val place: String
)
