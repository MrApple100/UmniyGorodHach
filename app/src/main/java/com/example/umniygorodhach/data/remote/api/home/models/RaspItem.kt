package com.example.umniygorodhach.data.remote.api.home.models

import kotlinx.serialization.Serializable

@Serializable
data class RaspItem(
    var id:Int,
    var id_user:Int,
    var title:String,
    var time:Int,
    var text:String
)
