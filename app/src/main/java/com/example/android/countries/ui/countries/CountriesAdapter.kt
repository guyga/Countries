package com.example.android.countries.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.countries.databinding.ItemCountryBinding
import com.example.android.countries.domain.model.Country

class CountriesAdapter(private val onCountryClickedListener: OnCountryClickedListener) :
    ListAdapter<Country, CountriesAdapter.CountryViewHolder>(CountryDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position), onCountryClickedListener)
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country, onCountryClickedListener: OnCountryClickedListener) {
            binding.root.setOnClickListener { onCountryClickedListener.onCountryClicked(country) }
            binding.country = country
            binding.executePendingBindings()
        }
    }

    class CountryDiff : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

    }

    class OnCountryClickedListener(private val block: (country: Country) -> Unit) {
        fun onCountryClicked(country: Country) = block(country)
    }
}