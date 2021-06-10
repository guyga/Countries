package com.example.android.countries.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.countries.databinding.FragmentCountriesBinding
import com.example.android.countries.domain.model.Country
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
        val countryClickListener = CountriesAdapter.OnCountryClickedListener(::onCountryClicked)
        _binding.countriesTable.adapter = CountriesAdapter(countryClickListener)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.navigateToCountry.observe(viewLifecycleOwner) {
            it?.let { country ->
                findNavController().navigate(
                    CountriesFragmentDirections.actionCountriesFragmentToDetailsFragment(
                        country, country.name
                    )
                )
                _viewModel.onNavigateToCountryCompleted()
            }
        }

        _viewModel.sort.observe(viewLifecycleOwner) {
            when (it.headerPosition) {
                0 -> adjustSortIcon(_binding.header.guideline1, it.direction)
                1 -> adjustSortIcon(_binding.header.guideline2, it.direction)
                2 -> adjustSortIcon(_binding.header.guideline3, it.direction)
            }
        }

        _binding.header.headerName.post { adjustHeadersPadding() }

        _binding.header.headerName.setOnClickListener { _viewModel.sort(0) }
        _binding.header.headerNativeName.setOnClickListener { _viewModel.sort(1) }
        _binding.header.headerArea.setOnClickListener { _viewModel.sort(2) }
    }

    private fun adjustHeadersPadding() {
        val sortIconWidth = _binding.header.sortIcon.width

        _binding.header.headerName.setPaddingRelative(
            _binding.header.headerName.paddingStart,
            _binding.header.headerName.paddingTop,
            sortIconWidth,
            _binding.header.headerName.paddingBottom
        )
        _binding.header.headerNativeName.setPaddingRelative(
            _binding.header.headerNativeName.paddingStart,
            _binding.header.headerNativeName.paddingTop,
            sortIconWidth,
            _binding.header.headerNativeName.paddingBottom
        )
        _binding.header.headerArea.setPaddingRelative(
            _binding.header.headerArea.paddingStart,
            _binding.header.headerArea.paddingTop,
            sortIconWidth,
            _binding.header.headerArea.paddingBottom
        )
    }

    private fun adjustSortIcon(toEndOf: Guideline, direction: CountriesViewModel.SortDirection) {
        // Set sort icon at the correct location
        val constraintLayout: ConstraintLayout = _binding.header.root
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect(
            _binding.header.sortIcon.id,
            ConstraintSet.END,
            toEndOf.id,
            ConstraintSet.END,
            0
        )
        constraintSet.applyTo(constraintLayout)

        // set sort icon direction
        _binding.header.sortIcon.rotationX =
            if (direction == CountriesViewModel.SortDirection.DSC) 0f else 180f
    }

    private fun onCountryClicked(country: Country) {
        _viewModel.navigateToCountry(country)
    }
}