package com.example.massive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.example.massive.R
import com.example.massive.databinding.ActivityRegisterBinding
import com.example.massive.view.activities.intro.intro

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)

    }

    override fun onClick(r: View) {
        when (r.id){
            R.id.btn_register->{

                val password = binding.etPassword.text.toString()
                val confirmPassword = binding.etConfirmPassword.text.toString()

                if (password != confirmPassword) {
                    binding.tilConfirmPassword.error = "Konfirmasi Password Salah, Coba Lagi!"
                } else {
                    binding.tilConfirmPassword.error = null
                    val intent = Intent(this@RegisterActivity, intro::class.java)
                    startActivity(intent)
                }
            }
            R.id.iv_back->{
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}