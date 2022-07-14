package com.example.umniygorodhach.data.remote.api.events.models.detail


import kotlinx.serialization.Serializable

@Serializable
data class Shift(
    val id: String,
    val beginTime: String,
    val endTime: String,
    val description: String? = null,
    val places: List<Place>
) {
   /* fun getTime(context: Context) = run {
        val shiftStartInstant = beginTime.fromIso8601ToInstant()
        val shiftEndInstant = endTime.fromIso8601ToInstant()
        "${
            shiftStartInstant.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            )
        }, ${
            beginTime.fromIso8601(
                context,
                ""
            )
        } — ${shiftEndInstant.hour.toString().padStart(2, '0')}:${
            shiftEndInstant.minute.toString().padStart(2, '0')
        }"
    }*/

    //val duration = endTime.fromIso8601ToInstant().hour - beginTime.fromIso8601ToInstant().hour
}