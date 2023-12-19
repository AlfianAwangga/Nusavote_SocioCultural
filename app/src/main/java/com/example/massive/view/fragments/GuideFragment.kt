package com.example.massive.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.massive.R
import com.example.massive.databinding.FragmentGuideBinding
import com.example.massive.model.GuideModel
import com.example.massive.view.adapters.GuideAdapter

class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding
    private lateinit var adapter: GuideAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuideBinding.inflate(inflater, container, false)

        init()
        setAdapterView()

        return binding.root
    }

    private fun init() {
        binding.rvGuide.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
    }

    private fun setAdapterView() {
        val listData: MutableList<GuideModel> = mutableListOf()

        judulGuide().forEachIndexed{index,nama ->
            listData.add(GuideModel(judulGuide()[index], deskripsiGuide()[index],isiGuide()[index]))
        }

        adapter = GuideAdapter(context,listData)
        binding.rvGuide.adapter = adapter
    }

    private fun isiGuide(): Array<String> = resources.getStringArray(R.array.isiGuide)

    private fun judulGuide(): Array<String> = resources.getStringArray(R.array.judulGuide)
    private fun deskripsiGuide(): Array<String> = resources.getStringArray(R.array.deskripsiGuide)

}