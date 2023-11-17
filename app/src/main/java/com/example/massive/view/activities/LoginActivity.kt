package com.example.massive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.massive.R
import com.example.massive.databinding.ActivityLoginBinding

private lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsernameLogin.addTextChangedListener(loginTextWatcher)
        binding.etPasswordLogin.addTextChangedListener(loginTextWatcher)

        binding.btnMasuk.setOnClickListener(this)
        binding.ivBackLogin.setOnClickListener(this)
    }

    private var loginTextWatcher = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            var username = binding.etUsernameLogin.text.toString().trim()
            var password = binding.etPasswordLogin.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()){
                binding.btnMasuk.isEnabled = true
            }else{
                binding.btnMasuk.isEnabled = false
            }
        }

        override fun afterTextChanged(s: Editable?) {}

    }

    override fun onClick(b: View) {
        when (b.id){
            R.id.btn_masuk->{
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.iv_back_login->{
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
