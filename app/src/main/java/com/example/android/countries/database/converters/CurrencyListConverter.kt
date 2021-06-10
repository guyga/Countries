package com.example.android.countries.database.converters

import androidx.room.TypeConverter
import com.example.android.countries.database.entities.CurrencyDatabaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrencyListConverter {
    @TypeConverter
    fun fromString(value: String?): List<CurrencyDatabaseEntity>? {
        val str: String = value ?: ""

        val listType = object : TypeToken<List<CurrencyDatabaseEntity>>() {}.type
        return Gson().fromJson<List<CurrencyDatabaseEntity>>(str, listType)
    }

    @TypeConverter
    fun fromList(list: List<CurrencyDatabaseEntity>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}