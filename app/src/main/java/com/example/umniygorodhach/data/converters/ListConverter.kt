package com.example.umniygorodhach.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.ToNumberPolicy
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


open class ListConverter<T> {

    private var gson: Gson? = null
    private var type: Type? = null

     constructor(type: Type?) {
        this.type = type
        gson = GsonBuilder()
            .serializeNulls()
            .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
            .create()
    }

    @TypeConverter
    public fun fromList(list: List<T>): String? {
        if (list == null) {
            return null
        }
        return gson?.toJson(list, type);

    }
    @TypeConverter
    public fun toList(string:String): List<T>? {
        if (string == null) {
            return null
        }
        return gson?.fromJson(string, type);

    }


}