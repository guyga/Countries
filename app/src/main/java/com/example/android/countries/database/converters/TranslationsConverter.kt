package com.example.android.countries.database.converters

import androidx.room.TypeConverter
import com.example.android.countries.database.entities.TranslationsDatabaseEntity
import com.google.gson.Gson

class TranslationsConverter {
    @TypeConverter
    fun fromString(value: String?): TranslationsDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<TranslationsDatabaseEntity>(
            str,
            TranslationsDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(movieDetails: TranslationsDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(movieDetails)
    }
}