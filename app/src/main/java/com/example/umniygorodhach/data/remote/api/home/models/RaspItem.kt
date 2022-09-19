package com.example.umniygorodhach.data.remote.api.home.models

import kotlinx.serialization.Serializable

@Serializable
data class RaspItem(
    var id:String,
    var id_user:String,
    var title:String,
    var time:String,
    var text:String
)
