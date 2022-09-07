package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.close.dao.TestDao
import com.example.umniygorodhach.data.remote.api.events.EventsApi

import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsApi: EventsApi,
    private val handler: ResponseHandler,
) {

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
	}

	suspend fun fetchEvent(eventId: String) = handler {
		eventsApi.getEvent(eventId)
	}

	suspend fun fetchEventSalary(eventId: String) = handler {
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