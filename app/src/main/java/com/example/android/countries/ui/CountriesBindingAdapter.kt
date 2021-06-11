package com.example.android.countries.ui.countries

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.countries.R
import com.example.android.countries.domain.model.Country
import com.example.android.countries.ui.details.BorderedCountriesAdapter

@BindingAdapter("loading")
fun bindLoading(view: View, isLoading: Boolean) {
    if (isLoading)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("error")
fun bindError(view: View, error: Boolean) {
    if (error)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("text")
fun bindText(textView: TextView, text: String) {
    textView.text = text
}

@BindingAdapter("area")
fun bindAreaText(textView: TextView, area: Double) {
    textView.text = area.toString()
}

@BindingAdapter("countries")
fun bindCountries(recyclerView: RecyclerView, countries: List<Country>?) {
    (recyclerView.adapter as CountriesAdapter).submitList(countries)
}

@BindingAdapter("countriesVisibility")
fun bindCountriesVisibility(view: View, countries: List<Country>?) {
    view.visibility = if (countries == null) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("nativeName")
fun bindNativeName(textView: TextView, nativeName: String) {
    textView.text = textView.resources.getString(R.string.title_native_name, nativeName)
}

@BindingAdapter("borderedCountryText")
fun bindBorderedCountriesText(textView: TextView, country: Country) {
    textView.text = textView.resources.getString(
        R.string.bordered_country_details,
        country.name,
        country.nativeName
    )
}

@BindingAdapter("borderedCountries")
fun bindBorderedCountriesText(recyclerView: RecyclerView, countries: List<Country>?) {
    (recyclerView.adapter as BorderedCountriesAdapter).submitList(countries)
}

@BindingAdapter("emptyBorders")
fun bindEmptyBorders(view: View, countries: List<Country>?) {
    countries?.let {
        view.visibility = if (countries.isEmpty()) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("borderedNotEmpty")
fun bindBorderedNotEmpty(view: View, countries: List<Country>?) {
    countries?.let {
        view.visibility = if (countries.isEmpty()) View.GONE else View.VISIBLE
    }
}