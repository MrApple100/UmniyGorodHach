package com.example.umniygorodhach.data.remote.api.events.models

import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import kotlinx.serialization.Serializable

@Serializable
data class EventRegistratePLayers(
    val idevent:String,
    val chosenPlayers: List<PlayerEntity>
)