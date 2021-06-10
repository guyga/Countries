package com.example.android.countries.networking.entities

import com.example.android.countries.database.entities.LanguageDatabaseEntity

class LanguageNetworkEntity(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
)

fun List<LanguageNetworkEntity>.toDatabaseLanguages(): List<LanguageDatabaseEntity> {
    return this.map {
        LanguageDatabaseEntity(
            iso639_1 = it.iso639_1,
            iso639_2 = it.iso639_2,
            name = it.name,
            nativeName = it.nativeName
        )
    }
}
