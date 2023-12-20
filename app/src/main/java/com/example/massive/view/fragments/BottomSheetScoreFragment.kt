package com.example.massive.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.massive.R
import com.example.massive.databinding.FragmentBottomSheetScoreBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetScoreFragment(
    private val activity: Activity,
    private val message: String,
    private val score: Int
) :
    BottomSheetDialogFragment(),
    View.OnClickListener {
    private lateinit var binding: FragmentBottomSheetScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetScoreBinding.inflate(inflater, container, false)

        if (score < 70) {
            binding.animResult.setAnimation("Animation - Quiz Failed.json")
            binding.tvStatusResult.text = "Coba Lagi Nanti Yah."
        } else {
            binding.animResult.setAnimation("Animation - Quiz Success.json")
            binding.tvStatusResult.text = "Kerja Bagus, Pertahankan."
        }

        binding.scoreResult.text = score.toString()
        binding.tvDetailResult.text = message

        binding.btnShare.setOnClickListener(this)
        binding.toHome.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toHome -> {
                val intent = Intent()
                activity.setResult(Activity.RESULT_OK, intent)
                activity.finish()
            }
            R.id.btn_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Saya Mendapatkan Score $score. Ayo Mainkan NusaVote, Aplikasi Yang Sangat Menyenangkan"
                )
                startActivity(Intent.createChooser(shareIntent, "Bagikan Ke"))
            }
        }
    }
}