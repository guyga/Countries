package com.example.android.countries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.countries.database.converters.StringListConverter
import com.example.android.countries.database.entities.CountryDatabaseEntity

@Database(entities = [CountryDatabaseEntity::class], version = 2)
@TypeConverters(value = [StringListConverter::class])
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
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}