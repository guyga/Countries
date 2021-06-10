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
)

fun List<CountryDatabaseEntity>.toDomainCountries(): List<Country> {
    return this.map {
        Country(
            name = it.name,
            nativeName = it.nativeName,
            area = it.area,
            borders = it.borders
        )
    }
}