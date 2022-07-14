package com.example.umniygorodhach.data.remote.api.events.models.detail


import kotlinx.serialization.Serializable

@Serializable
data class Property(
    val value: String,
    val status: String,
   // val userPropertyType: UserPropertyTypeModel
)