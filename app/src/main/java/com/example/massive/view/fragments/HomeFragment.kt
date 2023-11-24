package com.example.massive.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.massive.R
import com.example.massive.databinding.FragmentHomeBinding
import com.example.massive.view.activities.HomeActivity
import com.example.massive.view.adapters.HomePagerAdapter

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentHomeBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            Bagian1Fragment(requireContext()),
            Bagian2Fragment(requireContext()),
            Bagian3Fragment(requireContext())
        )

        val adapter = HomePagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return binding.root
    }
}