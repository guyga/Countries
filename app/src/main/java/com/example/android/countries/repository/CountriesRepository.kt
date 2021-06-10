package com.example.android.countries.repository

import android.content.Context
import com.example.android.countries.database.CountriesDao
import com.example.android.countries.database.CountriesDatabase
import com.example.android.countries.database.entities.toDomainCountries
import com.example.android.countries.domain.model.Country
import com.example.android.countries.networking.CountriesService
import com.example.android.countries.networking.entities.toDatabaseCountries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository private constructor(
    private val countriesDao: CountriesDao
) {

    suspend fun getAllCountries(): List<Country> {
        return withContext(Dispatchers.IO) {
            val cachedCountries = countriesDao.getAllCountries()
            if (!cachedCountries.isNullOrEmpty())
                return@withContext cachedCountries.toDomainCountries()

            val allCountries = CountriesService.countriesServiceApi.getAllCountries()
            val dbCountries = allCountries.toDatabaseCountries()
            countriesDao.insertCountries(dbCountries)
            return@withContext dbCountries.toDomainCountries()
        }
    }

    suspend fun getCountries(alpha3Codes: List<String>): List<Country> {
        return withContext(Dispatchers.IO) {
            return@withContext countriesDao.getCountries(alpha3Codes).toDomainCountries()
        }
    }

    companion object {
        private lateinit var INSTANCE: CountriesRepository

        fun getInstance(context: Context): CountriesRepository {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = CountriesRepository(
                        CountriesDatabase.getDatabase(context).countriesDao()
                    )
                }
                return INSTANCE
            }
        }
    }
}