package com.example.android.countries.ui.countries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.countries.databinding.FragmentCountriesBinding
import com.example.android.countries.repository.CountriesRepository

class CountriesFragment : Fragment() {

    private lateinit var _viewModel: CountriesViewModel
    private lateinit var _binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel = ViewModelProvider(
            this,
            CountriesViewModelFactory(CountriesRepository.getInstance(requireActivity()))
        ).get(CountriesViewModel::class.java)

        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.retry.setOnClickListener { _viewModel.getAllCountries() }

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countries?.let {
                Log.i("Guy", "countries: $countries")
            }
        }
    }
}