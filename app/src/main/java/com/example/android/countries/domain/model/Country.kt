package com.example.android.countries.domain.model

data class Country(
    var name: String,
    var nativeName: String,
    var area: Double,
    var borders: List<String>,
) {
}