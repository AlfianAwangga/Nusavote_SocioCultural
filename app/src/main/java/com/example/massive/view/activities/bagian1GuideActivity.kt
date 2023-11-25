package com.example.massive.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.massive.R
import com.example.massive.databinding.ActivityBagian1GuideBinding
import com.example.massive.databinding.FragmentGuideBinding
import com.example.massive.view.fragments.GuideFragment

private lateinit var binding: ActivityBagian1GuideBinding

class bagian1GuideActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBagian1GuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        binding.tvGuide1.text = bundle?.getString("judul")

        binding.ivBackGuide1.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.iv_back_guide_1 -> {
                val intent = Intent()
                intent.putExtra("key", 1)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}