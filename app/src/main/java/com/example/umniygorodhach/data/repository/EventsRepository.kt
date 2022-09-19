package com.example.umniygorodhach.data.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.close.dao.TestDao
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.EventsApi
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.remote.api.events.models.EventRegistratePLayers

import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsApi: EventsApi,
    private val handler: ResponseHandler,
) {
    //Mock Data
    val eventDetailDto = EventDetailDto(
        "1","Занятие по игре на Скрипке","Первое занятие на скрипке. Могут посетить все желающие","2022-21-09-17-50-00","2022-21-09-19-00-00","Moscow, Вернадксого 78",0,3,10,"Музыка","https://i.pinimg.com/originals/14/9b/25/149b25e8f29d2042da36563bf729553e.jpg"
    )
    val eventDetailDto2 = EventDetailDto(
        "2","Приезжает Лепс","Можно сходить на лепса","1644464444444","1644474444444","Moscow, Крокус холл",2000,1443,11000,"Концерт","https://i.pinimg.com/originals/14/9b/25/149b25e8f29d2042da36563bf729553e.jpg"
    )
    val eventDetailDto3 = EventDetailDto(
        "3","Киргизские танцы","Волонтеры устраивают сходку","1644484444444","1644494444444","Moscow, Метро Охотный Ряд",1000,13,100,"Танцы","https://i.pinimg.com/originals/14/9b/25/149b25e8f29d2042da36563bf729553e.jpg"
    )
    val listEvent = arrayListOf<EventDetailDto>(eventDetailDto,eventDetailDto2,eventDetailDto3)



	suspend fun fetchEvent() = handler {
	    listEvent as MutableList<EventDetailDto>
		//eventsApi.getEvents() as MutableList<EventDetailDto>
	}

    suspend fun registratePlayers(idevent: String, chosenPlayers: List<PlayerEntity>) = handler{
        val eventRegistratePlayers = EventRegistratePLayers(idevent,chosenPlayers)
        eventsApi.registratePlayers(eventRegistratePlayers)
    }


}