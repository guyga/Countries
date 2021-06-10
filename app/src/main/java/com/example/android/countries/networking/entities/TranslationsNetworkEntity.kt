package com.example.android.countries.networking.entities

import com.example.android.countries.database.entities.TranslationsDatabaseEntity

class TranslationsNetworkEntity(
    val de: String,
    val es: String,
    val fr: String,
    val ja: String,
    val it: String,
    val br: String,
    val pt: String,
) {
    fun toDatabaseTranslation(): TranslationsDatabaseEntity {
        return TranslationsDatabaseEntity(
            de = this.de,
            es = this.es,
            fr = this.fr,
            ja = this.ja,
            it = this.it,
            br = this.br,
            pt = this.pt
        )
    }
}