package com.example.massive.view.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.massive.R
import com.example.massive.databinding.ListStageBinding
import com.example.massive.databinding.ListUnitBinding
import com.example.massive.model.StageModel
import com.example.massive.model.UnitModel
import com.example.massive.view.activities.QuizActivity
import com.example.massive.view.activities.SplashQuizActivity
import com.example.massive.view.fragments.HomeFragment

class StageAdapter(
    private val context: Context?,
    private val list: List<StageModel>,
    private val onItemClick: (stage: StageModel, position:Int) -> Unit
) : RecyclerView.Adapter<StageAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListStageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListStageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.ibStage.setImageResource(this.image)

                when (position) {
                    1 -> {
                        binding.ibStage.setBackgroundResource(R.drawable.layer_shadow_grey_circle)
                        binding.ibStage.isEnabled = false
                    }

                    2 -> {
                        binding.ibStage.setBackgroundResource(R.drawable.layer_shadow_grey_circle)
                        binding.ibStage.isEnabled = false
                    }

                    3 -> {
                        binding.ibStage.setBackgroundResource(R.drawable.layer_shadow_grey_circle)
                        binding.ibStage.isEnabled = false
                    }

                    4 -> {
                        binding.ibStage.setBackgroundResource(R.drawable.layer_shadow_grey_circle)
                        binding.ibStage.isEnabled = false
                    }
                }

                binding.ibStage.setOnClickListener{
                    onItemClick.invoke(list[position], position+1)
                }
            }
        }
    }


}