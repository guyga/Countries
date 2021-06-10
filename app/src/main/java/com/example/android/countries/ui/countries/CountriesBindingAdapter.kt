package com.example.android.countries.ui.countries

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.countries.domain.model.Country

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