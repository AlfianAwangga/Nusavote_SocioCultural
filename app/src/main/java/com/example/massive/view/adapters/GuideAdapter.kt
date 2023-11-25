package com.example.massive.view.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.massive.databinding.ListGuideBinding
import com.example.massive.model.GuideModel
import com.example.massive.view.activities.bagian1GuideActivity

class GuideAdapter(private val context: Context?, private val list: List<GuideModel>) : RecyclerView.Adapter<GuideAdapter.ViewHolder>() {
    class ViewHolder(val binding : ListGuideBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListGuideBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.ivGuide.setImageResource(this.image_guide)
                binding.judulGuide.text = this.judul
                binding.deskripsiGuide.text = this.deskripsi

                binding.btnGuidePelajari.setOnClickListener{v:View ->
                    val position : Int = adapterPosition
                    val bundle = Bundle()
                    bundle.putString("judul", this.judul)
                    bundle.putString("deskripsi", this.deskripsi)

                    val intent = Intent(context, bagian1GuideActivity::class.java)
                    intent.putExtras(bundle)
                    context?.startActivity(intent)
                }

            }
        }

    }
}