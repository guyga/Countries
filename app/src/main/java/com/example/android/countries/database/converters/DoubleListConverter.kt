package com.example.android.countries.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoubleListConverter {
    @TypeConverter
    fun fromString(value: String?): List<Double>? {
        val str: String = value ?: ""

        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson<List<Double>>(str, listType)
    }

    @TypeConverter
    fun fromList(list: List<Double>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
