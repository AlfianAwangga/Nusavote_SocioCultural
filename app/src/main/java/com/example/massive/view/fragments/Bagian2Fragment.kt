package com.example.massive.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.massive.R
import com.example.massive.databinding.FragmentBagian2Binding
import com.example.massive.model.StageModel
import com.example.massive.model.UnitModel
import com.example.massive.view.adapters.UnitAdapter

class Bagian2Fragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentBagian2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBagian2Binding.inflate(inflater, container, false)

        binding.ivNavNext.setOnClickListener(this)
        binding.ivNavBack.setOnClickListener(this)

        binding.rvUnitBagian2.layoutManager = LinearLayoutManager(activity)
        setUnitAdapter()

        return binding.root
    }

    private fun setUnitAdapter() {
        val datalist: MutableList<UnitModel> = mutableListOf()

        namaUnit().forEachIndexed{ index, nama ->
            datalist.add(UnitModel(nama, deskripsiUnit()[index], panduanUnit()[index], stages()))
        }

        binding.rvUnitBagian2.adapter = UnitAdapter(context, datalist)
    }

    private fun namaUnit() : Array<String> {
        return resources.getStringArray(R.array.namaUnit)
    }
    private fun deskripsiUnit() : Array<String> {
        return resources.getStringArray(R.array.deskripsiUnit)
    }
    private fun panduanUnit() : Array<String> {
        return resources.getStringArray(R.array.panduanUnit)
    }
    private fun stages() : List<StageModel> {
        return listOf(
            StageModel(R.drawable.icon_forward),
            StageModel(R.drawable.icon_locked),
            StageModel(R.drawable.icon_locked),
            StageModel(R.drawable.icon_locked),
            StageModel(R.drawable.icon_task),
        )
    }

    override fun onClick(v: View) {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        when(v.id) {
            R.id.iv_nav_next -> {
                viewPager?.currentItem = 2
            }
            R.id.iv_nav_back -> {
                viewPager?.currentItem = 0
            }
        }
    }
}