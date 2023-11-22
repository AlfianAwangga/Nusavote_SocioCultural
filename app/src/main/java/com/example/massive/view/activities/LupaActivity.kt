package com.example.massive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.massive.R
import com.example.massive.databinding.ActivityLupaBinding

class LupaActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityLupaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLupaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etEmailLupa.addTextChangedListener(loginTextWatcher)

        binding.ivBackLupa.setOnClickListener(this)
        binding.btnKirim.setOnClickListener(this)
    }

    private var loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val email = binding.etEmailLupa.text.toString().trim()
            binding.btnKirim.isEnabled = email.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.iv_back_lupa -> {
                val intent = Intent(this@LupaActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_kirim -> {

            }
        }
    }
}