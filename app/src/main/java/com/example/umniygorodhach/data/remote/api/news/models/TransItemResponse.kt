package com.example.umniygorodhach.data.remote.api.news.models

import kotlinx.serialization.Serializable

@Serializable
data class TransItemResponse(
    val id:Int,
    val name:String,
    val picture:String,
    val broadURL:String
){

}
