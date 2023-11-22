package com.example.massive.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.massive.databinding.FragmentHomeBinding
import com.example.massive.view.adapters.HomePagerAdapter

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentHomeBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            Bagian1Fragment(),
            Bagian2Fragment(),
            Bagian3Fragment()
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