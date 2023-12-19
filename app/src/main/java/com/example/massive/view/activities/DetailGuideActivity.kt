package com.example.massive.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.massive.R
import com.example.massive.databinding.ActivityDetailGuideBinding

private lateinit var binding: ActivityDetailGuideBinding

class DetailGuideActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        binding.tvToolbarGuide.text = bundle?.getString("judul")
        binding.tvDetailJudulGuide.text = bundle?.getString("judul")
        binding.tvDetailDeskripsiGuide.text = bundle?.getString("deskripsi")
        binding.tvDetailIsiGuide.text = bundle?.getString("isi")

        binding.ivBackGuide.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.iv_back_guide -> {
                val intent = Intent()
                intent.putExtra("key", 1)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}