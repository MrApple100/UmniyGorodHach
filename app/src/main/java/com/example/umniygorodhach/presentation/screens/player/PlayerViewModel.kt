package com.example.umniygorodhach.presentation.screens.player

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.common.Resource
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.repository.EventsRepository
import com.example.umniygorodhach.data.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    private var _playerResourceFlow = MutableStateFlow<Resource<MutableList<PlayerEntity>>>(
        Resource.Loading)
    val playerResourceFlow = _playerResourceFlow.asStateFlow().also {
        fetchPlayers()
    }

    private var _playerFlow = MutableStateFlow<MutableList<PlayerEntity>>(emptyArray<PlayerEntity>().toMutableList())
    val playerFlow = _playerFlow.asStateFlow()


    private fun fetchPlayers() = _playerResourceFlow.emitInIO(viewModelScope) {
        playerRepository.fetchPlayers()
    }

    val snackbarHostState = SnackbarHostState()

    fun createPlayer(player : PlayerEntity,isSuccess: (Boolean) ->Unit) = viewModelScope.launch {
        playerRepository.insertPlayers(player).handle(
            onError = { msg ->
                isSuccess(false)

                snackbarHostState.showSnackbar(
                    message = msg
                )
            },
            onSuccess = {
                isSuccess(true)
                _playerFlow.value.add(player)
                snackbarHostState.showSnackbar(
                    message = "Successful"
                )
            }
        )
    }
    fun onDeletePlayer(player: PlayerEntity,onFinish: (Boolean) ->Unit) = viewModelScope.launch {
        playerRepository.deletePlayers(player).handle(
            onError = { msg ->
                onFinish(false)
                snackbarHostState.showSnackbar(
                    message = msg
                )
            },
            onSuccess = {
                onFinish(true)
                snackbarHostState.showSnackbar(
                    message = "Successful"
                )
            }
        )
    }

    fun onResourseSuccess(players: MutableList<PlayerEntity>) {
        _playerFlow.value = players

    }

    fun onRefresh() {
            fetchPlayers()
    }

}
