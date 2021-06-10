package com.example.android.countries.networking

import com.example.android.countries.networking.entities.CountryNetworkEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesServiceApi {
    @GET("all")
    suspend fun getAllCountries(): List<CountryNetworkEntity>
}

object CountriesService {
    val countriesServiceApi = Retrofit.Builder()
        .baseUrl("https://restcountries.eu/rest/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CountriesServiceApi::class.java)
}