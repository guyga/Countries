package com.example.android.countries.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.countries.domain.model.Country
import com.example.android.countries.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>?>()
    val countries: LiveData<List<Country>?>
        get() = _countries

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    init {
        getAllCountries()
    }

    fun getAllCountries() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _countries.value = null

            try {
                val countries = countriesRepository.getAllCountries()
                _countries.value = countries
                _error.value = false
            } catch (e: Exception) {
                _error.value = true
            } finally {
                _loading.value = false
            }
        }
    }
}