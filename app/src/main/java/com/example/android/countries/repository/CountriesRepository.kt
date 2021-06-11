package com.example.android.countries.repository

import android.content.Context
import android.util.Log
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

    /**
     * Retrieves all countries from the database.
     * If not found in database, through networking
     */
    suspend fun getAllCountries(): List<Country> {
        Log.i(TAG, "Getting all countries")
        return withContext(Dispatchers.IO) {
            val cachedCountries = countriesDao.getAllCountries()
            if (!cachedCountries.isNullOrEmpty()) {
                Log.i(
                    TAG,
                    "Retrieving countries from cache. ${cachedCountries.size} countries were found"
                )
                return@withContext cachedCountries.toDomainCountries()
            }

            Log.i(TAG, "Retrieving countries through networking")
            val allCountries = CountriesService.countriesServiceApi.getAllCountries()
            Log.i(TAG, "${allCountries.size} countries were fetched successfully")
            val dbCountries = allCountries.toDatabaseCountries()
            countriesDao.insertCountries(dbCountries)
            return@withContext dbCountries.toDomainCountries()
        }
    }

    /**
     * Retrieves all countries that match the given [Country.alpha3Code]
     */
    suspend fun getCountriesByCode(alpha3Codes: List<String>): List<Country> {
        Log.i(TAG, "Getting countries that match: $alpha3Codes")
        return withContext(Dispatchers.IO) {
            return@withContext countriesDao.getCountriesByCode(alpha3Codes).toDomainCountries()
        }
    }

    companion object {
        private val TAG = CountriesRepository::class.simpleName
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