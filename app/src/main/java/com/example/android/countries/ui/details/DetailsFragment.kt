package com.example.android.countries.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.android.countries.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args: DetailsFragmentArgs by navArgs()
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.country = args.country
        return binding.root
    }
}