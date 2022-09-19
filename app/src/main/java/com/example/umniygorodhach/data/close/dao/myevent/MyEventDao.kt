package com.example.umniygorodhach.data.close.dao.myevent

import androidx.room.*
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity

@Dao
interface MyEventsDao {

    @Query("SELECT * FROM myevent ")
    suspend fun getMyEvents() : List<MyEventEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg result: MyEventEntity)
    @Delete
    fun delete(result: MyEventEntity)
    @Update
    fun update(vararg result: MyEventEntity)

}