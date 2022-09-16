package com.example.umniygorodhach.data.remote.api.events.models


import com.example.umniygorodhach.data.remote.api.events.models.detail.Shift
import kotlinx.serialization.Serializable

@Serializable
data class EventDetailDto(
    val id: String,
    val title: String,
    val description: String,
    val beginTime: String,
    val endTime: String,
    val address: String,
    val money : Int,
    val currentParticipationCount: Int,
    val targetParticipationCount: Int,
    val eventType: String,
    val picture : String,

    ) {
    fun toEvent() =
        EventDetail(
            id = id,
            title = title,
            beginTime = beginTime,
            endTime = endTime,
            money = money,
            address = address,
            currentParticipationCount = currentParticipationCount,
            targetParticipationCount = targetParticipationCount,
            description = description,
            eventType = eventType

        )
}