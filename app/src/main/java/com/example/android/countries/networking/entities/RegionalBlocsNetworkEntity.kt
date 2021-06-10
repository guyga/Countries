package com.example.android.countries.networking.entities

import com.example.android.countries.database.entities.RegionalBlocsDatabaseEntity

class RegionalBlocsNetworkEntity(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<String>,
    val otherNames: List<String>,
)

fun List<RegionalBlocsNetworkEntity>.toDatabaseRegionalBlocs(): List<RegionalBlocsDatabaseEntity> {
    return this.map {
        RegionalBlocsDatabaseEntity(
            acronym = it.acronym,
            name = it.name,
            otherAcronyms = it.otherAcronyms,
            otherNames = it.otherNames
        )
    }
}
