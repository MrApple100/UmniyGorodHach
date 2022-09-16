package com.example.umniygorodhach.data.cachesqlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.umniygorodhach.data.close.dao.player.PlayerDao
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity

@Database(entities = arrayOf(PlayerEntity::class), version = 1)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}