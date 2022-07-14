package com.example.umniygorodhach.data.remote.api.events.models.detail


import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val id: String,
    val targetParticipantsCount: Int,
    val description: String? = null,
   // val equipment: List<DeviceModel>? = null,
    val participants: List<Participant>,
    val invited: List<Participant>,
    val wishers: List<Participant>,
    val unknowns: List<Participant>
)