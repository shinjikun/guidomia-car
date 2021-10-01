package com.leoilagan.guidomia.data.db.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)



    @TypeConverter
    fun jsonToList(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }



}