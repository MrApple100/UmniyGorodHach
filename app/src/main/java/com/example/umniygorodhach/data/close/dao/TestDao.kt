package com.example.umniygorodhach.data.close.dao

import androidx.room.*

@Dao
interface TestDao {
    @Query("SELECT * FROM test ORDER BY :order")
    fun getAll(order:String): List<TestEntity>
    @Insert
    fun insert(vararg result: TestEntity)
    @Delete
    fun delete(result: TestEntity)
    @Update
    fun update(vararg result: TestEntity)
}