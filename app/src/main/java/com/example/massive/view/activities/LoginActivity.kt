package com.example.massive.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.massive.R
import com.example.massive.auth.FacebookLoginAuth
import com.example.massive.auth.GoogleSignInAuth
import com.example.massive.databinding.ActivityLoginBinding
import com.example.massive.util.customSharePreference

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var pref : customSharePreference
    private lateinit var google : GoogleSignInAuth
    private lateinit var fb : FacebookLoginAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsernameLogin.addTextChangedListener(loginTextWatcher)
        binding.etPasswordLogin.addTextChangedListener(loginTextWatcher)

        pref = customSharePreference(this@LoginActivity)
        google = GoogleSignInAuth(this, binding.pbSigninGoogle)
        google.initialize()
        fb = FacebookLoginAuth(this)
        fb.initialize()

        binding.btnMasuk.setOnClickListener(this)
        binding.ivBackLogin.setOnClickListener(this)
        binding.tvLupaPassword.setOnClickListener(this)
        binding.btnGoogleLogin.setOnClickListener(this)
        binding.btnFacebookLogin.setOnClickListener(this)
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
            R.id.btn_google_login->{
                pref.saveLogin(1).let {
                    binding.pbSigninGoogle.visibility = View.VISIBLE
                    google.signInGoogle()
                }
            }
            R.id.btn_facebook_login->{
                pref.saveLogin(1).let {
                    fb.loginFacebook()
                }
            }
        }
    }
}
