package com.example.umniygorodhach.data.close.dao.myevent

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverter
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.models.EventDetail
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "myevent")
data class MyEventEntity(
@PrimaryKey
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
    var listPlayer:List<PlayerEntity>

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
            eventType = eventType,
            picture = picture

        )
}