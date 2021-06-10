package com.example.android.countries.database.entities

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.android.countries.database.converters.StringListConverter
import com.example.android.countries.domain.model.Country

@Entity(primaryKeys = [("name")])
class CountryDatabaseEntity(
    var name: String,
    var area: Double,
    @TypeConverters(StringListConverter::class)
    var borders: List<String>,
    var nativeName: String,
    var alpha3Code: String
) {
    fun toDomainCountry(): Country {
        return Country(
            name = this.name,
            nativeName = this.nativeName,
            area = this.area,
            borders = this.borders,
            alpha3Code = this.alpha3Code
        )
    }
}

fun List<CountryDatabaseEntity>.toDomainCountries(): List<Country> {
    return this.map { it.toDomainCountry() }
}