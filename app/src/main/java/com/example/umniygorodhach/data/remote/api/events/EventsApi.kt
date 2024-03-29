package com.example.umniygorodhach.data.remote.api.events

import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.remote.api.events.models.EventRegistratePLayers
import retrofit2.http.*

interface EventsApi {

/*

	@GET("Event")
	suspend fun getEvents(
		@Query("begin") begin: String? = null,
		@Query("end") end: String? = null
	) : List<EventModel>

	@GET("Event/user/{userId}")
	suspend fun getUserEvents(
		@Path("userId") userId: String,
		@Query("begin") begin: String? = null,
		@Query("end") end: String? = null
	): List<UserEventModel>
*/

	@GET("Event")
	suspend fun getEvents() : EventDetailDto

	@GET("Event/{eventId}")
	suspend fun getEvent(
		@Path("eventId") eventId: String
	) : EventDetailDto

	@POST("Event/{eventId}")
	suspend fun registratePlayers(
		@Body  eventRegistratePLayers: EventRegistratePLayers
	):EventDetailDto
}

	/*@GET("salary/v1/event/{eventId}")
	suspend fun getEventSalary(
		@Path("eventId") eventId: String
	) : EventSalary

	@POST("Event/wish/{placeId}/{roleId}")
	suspend fun applyForPlace(
		@Path("placeId") placeId: String,
		@Path("roleId") roleId: String
	) : Response<Unit>

	@GET("event/applications/invitations")
	suspend fun getInvitations() : List<EventInvitationDto>

	@POST("/api/Event/invitation/{placeId}/reject")
	suspend fun rejectInvitation(
		@Path("placeId") placeId: String
	) : Response<Unit>

	@POST("/api/Event/invitation/{placeId}/accept")
	suspend fun acceptInvitation(
		@Path("placeId") placeId: String
	) : Response<Unit>

	@GET("eventRole")
	suspend fun getEventRoles() : List<EventRoleModel>

}*/