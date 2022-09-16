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
        "0","Первое","Description","1644444444444","1644454444444","Moscow, Вернадксого 78",0,0,3,"Экскурсия","https://i.pinimg.com/originals/14/9b/25/149b25e8f29d2042da36563bf729553e.jpg"
    )
    val listEvent = arrayListOf<EventDetailDto>(eventDetailDto,eventDetailDto,eventDetailDto,eventDetailDto,eventDetailDto)

	/*suspend fun fetchPendingEvents() = handler {
		val now = nowAsIso8601()
		eventsApi.getEvents(begin = now)
	}*/

	/*suspend fun fetchAllEvents(
		begin: String? = null,
		end: String? = null
	) = handler {
		eventsApi.getEvents(begin, end)
	}

	suspend fun fetchUserEvents(
		userId: String,
		begin: String? = null,
		end: String? = null
	) = handler {
		eventsApi.getUserEvents(userId, begin, end)
	}*/

	suspend fun fetchEvent() = handler {
	    listEvent as MutableList<EventDetailDto>
		//eventsApi.getEvents() as MutableList<EventDetailDto>
	}

    suspend fun registratePlayers(idevent: String, chosenPlayers: List<PlayerEntity>) = handler{
        val eventRegistratePlayers = EventRegistratePLayers(idevent,chosenPlayers)
        eventsApi.registratePlayers(eventRegistratePlayers)
    }

    /*suspend fun fetchEventSalary(eventId: String) = handler {
        eventsApi.getEventSalary(eventId)
    }

    suspend fun applyForPlace(placeId: String, roleId: String) = handler {
        eventsApi.applyForPlace(placeId, roleId)
    }

    suspend fun fetchEventRoles() = handler {
        eventsApi.getEventRoles()
    }

    suspend fun fetchInvitations() = handler {
        eventsApi.getInvitations()
    }

    suspend fun rejectInvitation(placeId: String) = handler {
        eventsApi.rejectInvitation(placeId)
    }
    suspend fun acceptInvitation(placeId: String) = handler {
        eventsApi.acceptInvitation(placeId)
    }*/
}