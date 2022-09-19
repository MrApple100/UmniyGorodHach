package com.example.umniygorodhach.data.remote.api.events.models

import com.example.umniygorodhach.data.close.dao.myevent.MyEventEntity


data class EventDetail(
    val id: String,
    val title: String,
    val beginTime: String,
    val endTime: String,
    val money: Int,
    val address: String,
    val currentParticipationCount: Int,
    val targetParticipationCount: Int,
    val description: String,
    val eventType : String,
    val picture :String


    ){
    fun toMyEventEntity() =
        MyEventEntity(
            id = id,
            title = title,
            beginTime = beginTime,
            endTime = endTime,
            money = money,
            address = address,
            currentParticipationCount = currentParticipationCount,
            targetParticipationCount = targetParticipationCount,
            description = description,
            eventType = eventType,
            picture = picture,
            listPlayer = emptyList()

        )
}