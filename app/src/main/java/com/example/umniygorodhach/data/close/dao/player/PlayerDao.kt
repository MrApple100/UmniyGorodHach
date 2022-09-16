package com.example.umniygorodhach.data.close.dao.player

import androidx.room.*

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player ")
    fun getAll(): List<PlayerEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg result: PlayerEntity)
    @Delete
    fun delete(result: PlayerEntity)
    @Update
    fun update(vararg result: PlayerEntity)
}