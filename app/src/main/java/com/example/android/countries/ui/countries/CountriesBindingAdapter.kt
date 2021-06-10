package com.example.android.countries.ui.countries

import android.view.View
import androidx.databinding.BindingAdapter

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