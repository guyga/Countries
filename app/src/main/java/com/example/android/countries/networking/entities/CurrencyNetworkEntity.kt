package com.example.android.countries.networking.entities

import com.example.android.countries.database.entities.CurrencyDatabaseEntity

class CurrencyNetworkEntity(
    val code: String,
    val name: String,
    val symbol: String
)

fun List<CurrencyNetworkEntity>.toDatabaseCurrencies(): List<CurrencyDatabaseEntity> {
    return this.map {
        CurrencyDatabaseEntity(
            code = it.code,
            name = it.name,
            symbol = it.symbol
        )
    }
}
