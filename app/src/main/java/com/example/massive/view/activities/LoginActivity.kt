package com.example.massive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.massive.R
import com.example.massive.databinding.ActivityLoginBinding
import com.example.massive.util.customSharePreference

private lateinit var binding: ActivityLoginBinding
private lateinit var pref : customSharePreference

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsernameLogin.addTextChangedListener(loginTextWatcher)
        binding.etPasswordLogin.addTextChangedListener(loginTextWatcher)

        // inisialisasi custom share preference
        pref = customSharePreference(this@LoginActivity)


        binding.btnMasuk.setOnClickListener(this)
        binding.ivBackLogin.setOnClickListener(this)
        binding.tvLupaPassword.setOnClickListener(this)
    }

    private var loginTextWatcher = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            var username = binding.etUsernameLogin.text.toString().trim()
            var password = binding.etPasswordLogin.text.toString().trim()

            binding.btnMasuk.isEnabled = username.isNotEmpty() && password.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onClick(b: View) {
        when (b.id){
            R.id.btn_masuk->{
                pref.saveLogin(1).let {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.iv_back_login->{
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_lupa_password->{
                val intent = Intent(this@LoginActivity, LupaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
