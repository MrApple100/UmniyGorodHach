package com.example.umniygorodhach.presentation.screens.myevents

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.common.Resource
import com.example.umniygorodhach.data.close.dao.myevent.MyEventEntity
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.models.EventDetail
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.repository.EventsRepository
import com.example.umniygorodhach.data.repository.MyEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val myEventsRepository: MyEventsRepository,
) : ViewModel() {


        private var _eventsResponsesFlow = MutableStateFlow<Resource<MutableList<MyEventEntity>>>(
            Resource.Loading)
        val eventsResponsesFlow = _eventsResponsesFlow.asStateFlow().also {
            fetchEventData()
        }

        val snackbarHostState = SnackbarHostState()


        private fun fetchEventData() = _eventsResponsesFlow.emitInIO(viewModelScope) {
            myEventsRepository.fetchEvent()
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

        fun insertEventToMyEvent(event: EventDetail,chosenPlayers: List<PlayerEntity>) = viewModelScope.launch{
            val listmyevents = event.toMyEventEntity()
            listmyevents.listPlayer = chosenPlayers
            myEventsRepository.insertMyEvent(listmyevents).handle (
                onError = {msg ->
                    snackbarHostState.showSnackbar(
                        message = msg
                    )
                },
                onSuccess = {
                    snackbarHostState.showSnackbar(
                        message = "Successful"
                    )
                }
                    )
        }


        var cachedevents = mutableListOf<MyEventEntity>()

        private var _eventsFlow = MutableStateFlow(cachedevents)
        val eventsFlow = _eventsFlow.asStateFlow()

        fun onResourceSuccess(eventslist: MutableList<MyEventEntity>) {
            cachedevents = eventslist.map{it} as MutableList<MyEventEntity>

            _eventsFlow.value = cachedevents

        }
    }