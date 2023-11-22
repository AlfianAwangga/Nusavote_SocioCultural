package com.example.massive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.example.massive.R
import com.example.massive.databinding.ActivityRegisterBinding

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etNamaLengkap.addTextChangedListener(loginTextWatcher)
        binding.etUsername.addTextChangedListener(loginTextWatcher)
        binding.etEmail.addTextChangedListener(loginTextWatcher)
        binding.etPassword.addTextChangedListener(loginTextWatcher)
        binding.etConfirmPassword.addTextChangedListener(loginTextWatcher)

        binding.btnRegister.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }

    private var loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val namaLengkap = binding.etNamaLengkap.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            binding.btnRegister.isEnabled = username.isNotEmpty() && password.isNotEmpty()
                    && namaLengkap.isNotEmpty() && email.isNotEmpty() && confirmPassword.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onClick(r: View) {
        when (r.id){
            R.id.btn_register->{
                val namaLengkap = binding.etNamaLengkap.text.toString()
                val username = binding.etUsername.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val confirmPassword = binding.etConfirmPassword.text.toString()

                if (password != confirmPassword) {
                    binding.tilConfirmPassword.error = "Konfirmasi Password Salah, Coba Lagi!"
                } else {
                    binding.tilConfirmPassword.error = null
                    val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
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