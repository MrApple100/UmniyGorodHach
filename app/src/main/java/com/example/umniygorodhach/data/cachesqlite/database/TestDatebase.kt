package com.example.umniygorodhach.data.cachesqlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.umniygorodhach.data.close.dao.TestDao
import com.example.umniygorodhach.data.close.dao.TestEntity

@Database(entities = arrayOf(TestEntity::class), version = 1)
abstract class TestDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}