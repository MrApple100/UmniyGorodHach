package com.example.umniygorodhach.data.remote.api.events.models


import com.example.umniygorodhach.data.remote.api.events.models.detail.Shift
import kotlinx.serialization.Serializable

@Serializable
data class EventDetailDto(
    val id: String,
    val title: String,
    val description: String,
    val address: String,
   // val eventType: EventTypeModel,
    val shifts: List<Shift>
) {
   /* fun toEvent(salary: EventSalary?) =
        EventDetail(
            id = id,
            title = title,
            beginTime = shifts.minOf { it.beginTime },
            endTime = shifts.maxOf { it.endTime },
            salary = salary?.count,
            address = address,
            currentParticipationCount = shifts.sumOf { it.places.sumOf { it.participants.size } },
            targetParticipationCount = shifts.sumOf { it.places.sumOf { it.targetParticipantsCount } },
            description = description,
            type = eventType.title,
            shifts = shifts,
            shiftSalaries = salary?.shiftSalaries ?: emptyList(),
            placeSalaries = salary?.placeSalaries ?: emptyList()
        )*/
}