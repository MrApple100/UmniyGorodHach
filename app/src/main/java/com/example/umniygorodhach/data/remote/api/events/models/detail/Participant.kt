package com.example.umniygorodhach.data.remote.api.events.models.detail


import kotlinx.serialization.Serializable

@Serializable
data class Participant(
    //val user: UserResponse,
    //val eventRole: EventRoleModel,
    val creationTime: String? = null,
    val doneTime: String? = null
)