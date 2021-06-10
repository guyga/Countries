package com.example.android.countries.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.countries.databinding.ItemBorderedCountryBinding
import com.example.android.countries.domain.model.Country

class BorderedCountriesAdapter :
    ListAdapter<Country, BorderedCountriesAdapter.CountryViewHolder>(CountryDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            ItemBorderedCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CountryViewHolder(private val binding: ItemBorderedCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
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
}