package com.example.android.countries.database.converters

import androidx.room.TypeConverter
import com.example.android.countries.database.entities.RegionalBlocsDatabaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegionalBlocListConverter {
    @TypeConverter
    fun fromString(value: String?): List<RegionalBlocsDatabaseEntity>? {
        val str: String = value ?: ""

        val listType = object : TypeToken<List<RegionalBlocsDatabaseEntity>>() {}.type
        return Gson().fromJson<List<RegionalBlocsDatabaseEntity>>(str, listType)
    }

    @TypeConverter
    fun fromList(list: List<RegionalBlocsDatabaseEntity>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}