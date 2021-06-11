package com.example.android.countries.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.countries.databinding.FragmentDetailsBinding
import com.example.android.countries.repository.CountriesRepository

class DetailsFragment : Fragment() {

    private lateinit var _viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel = ViewModelProvider(
            this,
            DetailsViewModelFactory(CountriesRepository.getInstance(requireActivity()))
        ).get(DetailsViewModel::class.java)

        val args: DetailsFragmentArgs by navArgs()

        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.country = args.country
        binding.viewModel = _viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.borderedList.adapter = BorderedCountriesAdapter()
        _viewModel.setCountry(args.country)
        return binding.root
    }
}