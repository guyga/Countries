package com.example.android.countries.database.converters

import androidx.room.TypeConverter
import com.example.android.countries.database.entities.LanguageDatabaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguageListConverter {
    @TypeConverter
    fun fromString(value: String?): List<LanguageDatabaseEntity>? {
        val str: String = value ?: ""

        val listType = object : TypeToken<List<LanguageDatabaseEntity>>() {}.type
        return Gson().fromJson<List<LanguageDatabaseEntity>>(str, listType)
    }

    @TypeConverter
    fun fromList(list: List<LanguageDatabaseEntity>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}