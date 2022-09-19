package com.example.umniygorodhach.data.cachesqlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.umniygorodhach.data.converters.ListConverter
import com.example.umniygorodhach.data.close.dao.myevent.MyEventEntity
import com.example.umniygorodhach.data.close.dao.myevent.MyEventsDao
import com.example.umniygorodhach.data.converters.PlayerListConverter

@Database(entities = arrayOf(MyEventEntity::class), version = 1)
@TypeConverters(PlayerListConverter::class)
abstract class MyEventDatabase : RoomDatabase() {
    abstract fun myeventDao(): MyEventsDao
}