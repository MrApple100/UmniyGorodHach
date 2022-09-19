package com.example.umniygorodhach.data.remote.api.myevents

import androidx.room.Dao
import androidx.room.Query
import com.example.umniygorodhach.data.close.dao.myevent.MyEventEntity
import retrofit2.http.GET

@Dao
interface MyEventsApi {

    @Query("SELECT * FROM myevent ")
    suspend fun getMyEvents() : MyEventEntity

}