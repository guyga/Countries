package com.example.android.countries.ui.details

import android.util.Log
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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun setCountry(country: Country) {
        Log.i(TAG, "Selected country is ${country.name}")
        _loading.value = true
        viewModelScope.launch {
            try {
                if (country.borders.isNotEmpty()) {
                    Log.i(
                        TAG,
                        "${country.name} is bordered with: ${country.borders}, retrieving countries now"
                    )
                    val countries = countriesRepository.getCountriesByCode(country.borders)
                    Log.i(
                        TAG,
                        "Bordered countries were retrieved successfully: ${countries.map { it.name }}"
                    )
                    _borderedCountries.value = countries
                } else {
                    Log.i(TAG, "${country.name} is not bordered with any other country")
                    _borderedCountries.value = listOf()
                }
            } catch (e: Exception) {
                Log.e(
                    TAG,
                    "Error retrieving ${country.name} borders, showing an empty border UI instead. ${e.message}"
                )
                _borderedCountries.value = listOf()
            } finally {
                _loading.value = false
            }
        }
    }

    companion object {
        private val TAG = DetailsViewModel::class.simpleName
    }
}