package com.example.android.countries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.countries.database.entities.CountryDatabaseEntity

@Dao
interface CountriesDao {

    @Query("SELECT * from CountryDatabaseEntity")
    suspend fun getAllCountries(): List<CountryDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryDatabaseEntity>)

    @Query("SELECT * from CountryDatabaseEntity WHERE alpha3Code IN (:alpha3Codes)")
    suspend fun getCountriesByCode(alpha3Codes: List<String>): List<CountryDatabaseEntity>
}