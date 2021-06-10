package com.example.android.countries.networking.entities

import com.example.android.countries.database.entities.CountryDatabaseEntity

class CountryNetworkEntity(
    var name: String,
    var topLevelDomain: List<String>,
    var alpha2Code: String,
    var alpha3Code: String,
    var callingCodes: List<String>,
    var capital: String,
    var altSpellings: List<String>,
    var region: String,
    var subregion: String,
    var population: Long,
    var latlng: List<Double>,
    var demonym: String,
    var area: Double,
    var gini: Double,
    var timezones: List<String>,
    var borders: List<String>,
    var nativeName: String,
    var numericCode: Int,
    var currencies: List<CurrencyNetworkEntity>,
    var languages: List<LanguageNetworkEntity>,
    var translations: TranslationsNetworkEntity,
    var flag: String,
    var regionalBlocs: List<RegionalBlocsNetworkEntity>,
    var cioc: String
)

fun List<CountryNetworkEntity>.toDatabaseCountries(): List<CountryDatabaseEntity> {
    return this.map {
        CountryDatabaseEntity(
            name = it.name,
            area = it.area,
            borders = it.borders,
            nativeName = it.nativeName,
        )
    }
}