package com.example.android.countries.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.countries.repository.CountriesRepository

class CountriesViewModelFactory(
    private val countriesRepository: CountriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java))
            return CountriesViewModel(countriesRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}