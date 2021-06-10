package com.example.android.countries.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.countries.domain.model.Country
import com.example.android.countries.repository.CountriesRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {

    private val _borderedCountries = MutableLiveData<List<Country>>()
    val borderedCountries: LiveData<List<Country>>
        get() = _borderedCountries

    fun setCountry(country: Country) {
        viewModelScope.launch {
            val countries = countriesRepository.getCountries(country.borders)
            _borderedCountries.value = countries
        }
    }

}