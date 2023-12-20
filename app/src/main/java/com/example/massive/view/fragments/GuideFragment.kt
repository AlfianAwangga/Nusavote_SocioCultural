package com.example.massive.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.massive.R
import com.example.massive.databinding.FragmentGuideBinding
import com.example.massive.model.GuideModel
import com.example.massive.view.adapters.GuideAdapter
import okhttp3.internal.notify
import java.util.Locale

class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding
    private lateinit var adapter: GuideAdapter
    private lateinit var filteredList: ArrayList<GuideModel>
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
        binding.rvGuide.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun setAdapterView() {
        val listData: MutableList<GuideModel> = mutableListOf()

        judulGuide().forEachIndexed { index, nama ->
            listData.add(
                GuideModel(
                    judulGuide()[index],
                    deskripsiGuide()[index],
                    isiGuide()[index]
                )
            )
        }

        adapter = GuideAdapter(context, listData)
        binding.rvGuide.adapter = adapter


        // search view
        filteredList = ArrayList(listData)

        binding.searchGuide.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                listData.clear()

                val searchText = newText!!.toLowerCase(Locale.getDefault())

                if (searchText.isNotEmpty()) {

                    filteredList.forEach {
                        if (it.judul.toLowerCase(Locale.getDefault()).contains(searchText) ||
                            it.deskripsi.toLowerCase(Locale.getDefault()).contains(searchText)
                        ) {
                            listData.add(it)
                        }
                    }

                    adapter.notifyDataSetChanged()

                } else {
                    listData.clear()
                    listData.addAll(filteredList)
                    adapter.notifyDataSetChanged()
                }
                return false
            }

        })
    }


    private fun isiGuide(): Array<String> = resources.getStringArray(R.array.isiGuide)

    private fun judulGuide(): Array<String> = resources.getStringArray(R.array.judulGuide)
    private fun deskripsiGuide(): Array<String> = resources.getStringArray(R.array.deskripsiGuide)

}