package com.example.android.countries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.countries.database.converters.*
import com.example.android.countries.database.entities.CountryDatabaseEntity

@Database(entities = [CountryDatabaseEntity::class], version = 1)
@TypeConverters(value = [StringListConverter::class, DoubleListConverter::class, CurrencyListConverter::class, LanguageListConverter::class, RegionalBlocListConverter::class, TranslationsConverter::class])
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao

    companion object {
        private lateinit var INSTANCE: CountriesDatabase

        fun getDatabase(context: Context): CountriesDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CountriesDatabase::class.java,
                        "countries-database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}