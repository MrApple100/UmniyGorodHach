package com.example.traininghakatonsever.presentation.screens.events

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.umniygorodhach.data.repository.EventsRepository

import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalPagerApi
@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: EventsRepository,
	//private val authStateStorage: AuthStateStorage
) : ViewModel() {
/*

	private val userId = runBlocking { authStateStorage.userIdFlow.first() }



	private var _beginEventsDate = MutableStateFlow(Clock.System.now().minus(7, DateTimeUnit.DAY).toEpochMilliseconds())
	val beginEventsDate = _beginEventsDate.asStateFlow()
	private var _endEventsDate = MutableStateFlow(Clock.System.now().toEpochMilliseconds())
	val endEventsDate = _endEventsDate.asStateFlow()


	val pagerState = PagerState()

	val swipingState = SwipeableState(SwipingStates.EXPANDED)

	val allEventsListState = LazyListState()
	val userEventsListState = LazyListState()

	private var _isDateSelectionMade = MutableStateFlow(false)
	val isDateSelectionMade = _isDateSelectionMade.asStateFlow()


	private val _eventsListResponseFlow =
		MutableStateFlow<Resource<List<EventModel>>>(Resource.Loading)
	val eventsListResponsesFlow = _eventsListResponseFlow.asStateFlow().also {
		fetchPendingEvents()
	}

	private val _userEventsListResponseFlow =
		MutableStateFlow<Resource<List<UserEventModel>>>(Resource.Empty)
	val userEventsListResponsesFlow = _userEventsListResponseFlow.asStateFlow()

	private val _pastEventsListResponseFlow =
		MutableStateFlow<Resource<List<EventModel>>>(Resource.Empty)
	val pastEventsListResponseFlow = _pastEventsListResponseFlow.asStateFlow()

	private var _invitationsResourceFlow = MutableStateFlow<Resource<List<EventInvitationDto>>>(
        Resource.Loading)
	val invitationsResourceFlow = _invitationsResourceFlow.asStateFlow().also { fetchInvitations() }

	private var _invitationsCountFlow = MutableStateFlow(0)
	val invitationsCountFlow = _invitationsCountFlow.asStateFlow()

	private var cachedEventList = emptyList<EventModel>()
	private var cachedUserEventList = emptyList<UserEventModel>()
	private var cachedPastEventList = emptyList<EventModel>()

	private var _eventsFlow = MutableStateFlow(cachedEventList)
	val eventsFlow = _eventsFlow.asStateFlow()

	private var _userEventsFlow = MutableStateFlow(cachedUserEventList)
	val userEventsFlow = _userEventsFlow.asStateFlow()

	private var _pastEventsFlow = MutableStateFlow(cachedPastEventList)
	val pastEventsFlow = _pastEventsFlow.asStateFlow()

	private var _showPastEvents = MutableStateFlow(false)
	val showPastEvents = _showPastEvents.asStateFlow()

	fun toggleShowPastEvents(show: Boolean) = viewModelScope.launch {
		_showPastEvents.value = show
	}.also {
		if (!show) return@also
		_pastEventsListResponseFlow.emitInIO(viewModelScope) {
			repository.fetchAllEvents(
				end = nowAsIso8601()
			)
		}
	}

	fun fetchAllEvents(begin: String? = null, end: String? = null) = _eventsListResponseFlow.emitInIO(viewModelScope) {
		repository.fetchAllEvents(begin, end)
	}

	fun fetchPendingEvents() = _eventsListResponseFlow.emitInIO(viewModelScope) {
		_isDateSelectionMade.value = false
		repository.fetchPendingEvents()
	}

	fun fetchUserEvents(begin: String? = null, end: String? = null) = _userEventsListResponseFlow.emitInIO(viewModelScope) {
		repository.fetchUserEvents(userId, begin, end)
	}


	val snackbarHostState = SnackbarHostState()

	fun setEventsDates(begin: Long, end: Long) {
		_showPastEvents.value = false
		_isDateSelectionMade.value = true
		_beginEventsDate.value = begin
		_endEventsDate.value = end
		fetchAllEvents(
			begin = begin.toMoscowDateTime().date.toString(),
			end = end.toMoscowDateTime().date.toString()
		)
	}

	fun fetchInvitations() = _invitationsResourceFlow.emitInIO(viewModelScope) {
		val resource = repository.fetchInvitations()
		resource.handle(
			onSuccess = {
				_invitationsCountFlow.value = it.size
			}
		)
		resource
	}

	fun rejectInvitation(
		placeId: String,
		successMessage: String,
		onFinish: () -> Unit
	) = viewModelScope.launch {
		repository.rejectInvitation(placeId).handle(
			onSuccess = {
				onFinish()
				fetchInvitations()
				snackbarHostState.showSnackbar(it.errorBody()?.string() ?: successMessage)
			},
			onError = {
				onFinish()
				snackbarHostState.showSnackbar(it)
			}
		)
	}
	fun acceptInvitation(
		placeId: String,
		successMessage: String,
		onFinish: () -> Unit
	) = viewModelScope.launch {
		repository.acceptInvitation(placeId).handle(
			onSuccess = {
				onFinish()
				fetchInvitations()
				snackbarHostState.showSnackbar(it.errorBody()?.string() ?: successMessage)
			},
			onError = {
				onFinish()
				snackbarHostState.showSnackbar(it)
			}
		)
	}


	private var searchQuery = ""

	fun onSearch(query: String) {
		searchQuery = query
		_eventsFlow.value = cachedEventList.filter { filterSearchResult(it, query) }
		_userEventsFlow.value = cachedUserEventList.filter { filterSearchResult(it, query) }
		if (showPastEvents.value)
			_pastEventsFlow.value = cachedPastEventList.filter { filterSearchResult(it, query) }
	}

	fun onResourceSuccess(events: List<EventModel>) {
		cachedEventList = events
		_eventsFlow.value = events
	}

	fun onUserResourceSuccess(events: List<UserEventModel>) {
		cachedUserEventList = events.distinctBy { it.id }
		_userEventsFlow.value = cachedUserEventList
	}

	fun onPastResourceSuccess(events: List<EventModel>) {
		cachedPastEventList = events.filterNot { cachedEventList.contains(it) }
		_pastEventsFlow.value = cachedPastEventList
	}

	private fun filterSearchResult(event: EventModel, query: String) = event.run {
		title.contains(query.trim(), ignoreCase = true) ||
		eventType.title.contains(query.trim(), ignoreCase = true)
	}
	private fun filterSearchResult(event: UserEventModel, query: String) = event.run {
		title.contains(query.trim(), ignoreCase = true) ||
		eventType.title.contains(query.trim(), ignoreCase = true)
	}
*/

}
