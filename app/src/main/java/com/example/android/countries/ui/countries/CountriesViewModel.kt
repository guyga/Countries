package com.example.android.countries.ui.countries

import android.util.Log
import androidx.lifecycle.*
import com.example.android.countries.domain.model.Country
import com.example.android.countries.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _countriesUnsorted = MutableLiveData<List<Country>?>()
    private val _countries = MediatorLiveData<List<Country>?>()
    val countries: LiveData<List<Country>?>
        get() = _countries

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _navigateToCountry = MutableLiveData<Country?>()
    val navigateToCountry: LiveData<Country?>
        get() = _navigateToCountry

    private val _sort = MutableLiveData<SortState>(SortState(0, SortDirection.DSC))
    val sort: LiveData<SortState>
        get() = _sort

    private lateinit var _comparatorNameDsc: Comparator<Country>
    private lateinit var _comparatorNameAsc: Comparator<Country>
    private lateinit var _comparatorNativeNameDsc: Comparator<Country>
    private lateinit var _comparatorNativeNameAsc: Comparator<Country>
    private lateinit var _comparatorAreaDsc: Comparator<Country>
    private lateinit var _comparatorAreaAsc: Comparator<Country>

    init {

        createComparators()

        _countries.addSource(_countriesUnsorted) {
            it?.let {
                Log.i(TAG, "Countries were received - sorting now")
                _countries.value = sort(it, _sort.value!!)
            }
        }
        _countries.addSource(_sort) { sortState ->
            Log.i(
                TAG,
                "Sort state was set to column ${sortState.headerPosition} direction ${sortState.direction.name}"
            )
            _countriesUnsorted.value?.let { countries ->
                _countries.value = sort(countries, sortState)
            }
        }

        getAllCountries()
    }

    /**
     * Initializing all the required sorting comparators
     */
    private fun createComparators() {
        _comparatorNameDsc = Comparator<Country> { o1, o2 -> o1.name.compareTo(o2.name) }
        _comparatorNameAsc = Comparator<Country> { o1, o2 -> o2.name.compareTo(o1.name) }
        _comparatorNativeNameDsc =
            Comparator<Country> { o1, o2 -> o1.nativeName.compareTo(o2.nativeName) }
        _comparatorNativeNameAsc =
            Comparator<Country> { o1, o2 -> o2.nativeName.compareTo(o1.nativeName) }
        _comparatorAreaDsc = Comparator<Country> { o1, o2 -> o1.area.compareTo(o2.area) }
        _comparatorAreaAsc = Comparator<Country> { o1, o2 -> o2.area.compareTo(o1.area) }
    }

    fun getAllCountries() {
        Log.i(TAG, "Getting all countries")
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _countriesUnsorted.value = null
            _countries.value = null

            try {
                val countries = countriesRepository.getAllCountries()
                Log.i(TAG, "${countries.size} countries were received successfully")
                _countriesUnsorted.value = countries
                _error.value = false
            } catch (e: Exception) {
                Log.e(TAG, "Error retrieving countries. ${e.message}")
                _error.value = true
            } finally {
                _loading.value = false
            }
        }
    }

    fun navigateToCountry(country: Country) {
        _navigateToCountry.value = country
    }

    fun onNavigateToCountryCompleted() {
        _navigateToCountry.value = null
    }

    /**
     * Sorting state was changed,
     * The new sorting would be by the given column, direction - DSC,
     * unless its the same column as the current one - then direction would be ASC.
     */
    fun sort(headerPosition: Int) {
        val direction: SortDirection = if (_sort.value!!.headerPosition == headerPosition)
            if (_sort.value!!.direction == SortDirection.DSC)
                SortDirection.ASC
            else
                SortDirection.DSC
        else
            SortDirection.DSC

        _sort.value = SortState(headerPosition, direction)
    }

    /**
     * Actual sorting. First retrieve the correct comparator, then sort
     */
    private fun sort(countries: List<Country>, sortState: SortState): List<Country> {
        val comparator: Comparator<Country> = if (sortState.direction == SortDirection.ASC) {
            Log.i(TAG, "Sorting ASC, by column ${sortState.headerPosition}")
            when (sortState.headerPosition) {
                0 -> _comparatorNameAsc
                1 -> _comparatorNativeNameAsc
                2 -> _comparatorAreaAsc
                else -> _comparatorNameDsc
            }
        } else {
            Log.i(TAG, "Sorting DSC, by column ${sortState.headerPosition}")
            when (sortState.headerPosition) {
                0 -> _comparatorNameDsc
                1 -> _comparatorNativeNameDsc
                2 -> _comparatorAreaDsc
                else -> _comparatorNameDsc
            }
        }

        return countries.sortedWith(comparator)
    }


    class SortState(
        var headerPosition: Int,
        var direction: SortDirection
    )

    enum class SortDirection { ASC, DSC }

    companion object {
        private val TAG = CountriesViewModel::class.simpleName
    }
}