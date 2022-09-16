package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.close.dao.player.PlayerDao
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.EventsApi
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerDao: PlayerDao,
    private val handler: ResponseHandler,
) {

    suspend fun fetchPlayers() = handler {
        playerDao.getAll() as MutableList<PlayerEntity>
    }
    suspend fun insertPlayers(player:PlayerEntity) = handler {
        playerDao.insert(player)
    }
    suspend fun deletePlayers(player:PlayerEntity) = handler {
        playerDao.delete(player)
    }
    suspend fun updatePlayers(player:PlayerEntity) = handler {
        playerDao.update(player)
    }
}