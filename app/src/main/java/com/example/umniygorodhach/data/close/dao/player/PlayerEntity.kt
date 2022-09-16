package com.example.umniygorodhach.data.close.dao.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "Lastname") var lastname: String,
    @ColumnInfo(name = "Firstname") var firstname: String,
    @ColumnInfo(name = "Middlename") var middlename: String,
    @ColumnInfo(name = "Age") var age: Int,
)