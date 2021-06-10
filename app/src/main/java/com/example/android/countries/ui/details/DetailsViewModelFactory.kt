package com.example.android.countries.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.countries.repository.CountriesRepository

class DetailsViewModelFactory(
    private val countriesRepository: CountriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            return DetailsViewModel(countriesRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}