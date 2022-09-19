package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.close.dao.myevent.MyEventEntity
import com.example.umniygorodhach.data.close.dao.myevent.MyEventsDao
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.EventsApi
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.remote.api.myevents.MyEventsApi
import javax.inject.Inject

class MyEventsRepository @Inject constructor(
    private val myEventsDao: MyEventsDao,
    private val handler: ResponseHandler,
) {

    suspend fun fetchEvent() = handler {
        myEventsDao.getMyEvents() as MutableList<MyEventEntity>
    }

    suspend fun insertMyEvent(myevent: MyEventEntity) = handler {
        myEventsDao.insert(myevent)
    }

    suspend fun deleteMyEvent(myEvent:MyEventEntity) = handler {
        myEventsDao.delete(myEvent)
    }
    suspend fun updateMyEvent(myEvent:MyEventEntity) = handler {
        myEventsDao.update(myEvent)
    }
}