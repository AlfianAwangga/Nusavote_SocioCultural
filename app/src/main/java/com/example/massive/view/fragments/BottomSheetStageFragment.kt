package com.example.massive.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.massive.R
import com.example.massive.databinding.FragmentBottomSheetStageBinding
import com.example.massive.view.activities.SplashQuizActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetStageFragment(val nama: String, val desk: String, val stage: Int) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetStageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetStageBinding.inflate(inflater, container, false)

        binding.tvUnitBottom.text = "$nama - Stage $stage"
        binding.tvDeskripsiBottom.text = desk

        binding.btnMulaiQuiz.setOnClickListener{
            val intent = Intent(activity, SplashQuizActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}