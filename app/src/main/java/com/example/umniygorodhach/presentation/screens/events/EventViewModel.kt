
package com.example.umniygorodhach.presentation.screens.events

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.umniygorodhach.common.Resource
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.models.EventDetail
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import com.example.umniygorodhach.data.repository.EventsRepository
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
   // private val savedState: SavedStateHandle
) : ViewModel() {
//	private val eventId: String = savedState["eventId"]!!

	private var _eventsResponsesFlow = MutableStateFlow<Resource<MutableList<EventDetailDto>>>(
        Resource.Loading)
	val eventsResponsesFlow = _eventsResponsesFlow.asStateFlow().also {
		fetchEventData()
	}

	//private var _eventRoles = MutableStateFlow<List<EventRole>>(emptyList())
	/*val eventRoles = _eventRoles.asStateFlow().also {
		fetchEventRoles()
	}*/

	val snackbarHostState = SnackbarHostState()


	private fun fetchEventData() = _eventsResponsesFlow.emitInIO(viewModelScope) {
		eventsRepository.fetchEvent()
	}



	/*fun onPlaceApply(
		placeId: String,
		roleId: String,
		successMessage: String,
		onFinish: () -> Unit
	) = viewModelScope.launch {
		eventsRepository.applyForPlace(placeId, roleId).handle(
			onSuccess = {
				onFinish()
				fetchEventData()
				showSnackbar(it.errorBody()?.string() ?: successMessage)
			},
			onError = {
				onFinish()
				showSnackbar(it)
			}
		)
	}*/

	private suspend fun showSnackbar(text: String) {
		snackbarHostState.showSnackbar(text)
	}

	fun registratePlayers(idevent: String, chosenPlayers: List<PlayerEntity>,isSuccess: (Boolean) ->Unit) = viewModelScope.launch{
		eventsRepository.registratePlayers(idevent,chosenPlayers).handle(
			onError = { msg ->
				isSuccess(false)
				snackbarHostState.showSnackbar(
					message = msg
				)
			},
			onSuccess = {
				isSuccess(true)
				snackbarHostState.showSnackbar(
					message = "Successful"
				)
			}
		)
	}


	var cachedevents = mutableListOf<EventDetail>()

	private var _eventsFlow = MutableStateFlow(cachedevents)
	val eventsFlow = _eventsFlow.asStateFlow()

	fun onResourceSuccess(eventslist: MutableList<EventDetailDto>) {
		cachedevents = eventslist.map{it.toEvent()} as MutableList<EventDetail>

		_eventsFlow.value = cachedevents

	}
}
