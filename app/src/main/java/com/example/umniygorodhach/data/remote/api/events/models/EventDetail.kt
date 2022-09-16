package com.example.umniygorodhach.data.remote.api.events.models


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
    val eventType : String


    )