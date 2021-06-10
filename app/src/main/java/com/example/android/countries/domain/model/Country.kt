package com.example.android.countries.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    var name: String,
    var nativeName: String,
    var area: Double,
    var borders: List<String>,
    var alpha3Code: String
) : Parcelable {
}