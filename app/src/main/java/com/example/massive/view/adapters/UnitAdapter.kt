package com.example.massive.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.massive.databinding.ListUnitBinding
import com.example.massive.model.UnitModel

class UnitAdapter(private val context: Context?, private val list: List<UnitModel>) : RecyclerView.Adapter<UnitAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListUnitBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvUnitNama.text = this.nama
                binding.tvUnitDeskripsi.text = this.deskripsi
                binding.rvStage.layoutManager = LinearLayoutManager(context)
                binding.rvStage.adapter = StageAdapter(context, this.stage)
            }
        }
    }


}