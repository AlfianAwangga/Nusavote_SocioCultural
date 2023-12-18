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
import com.example.massive.databinding.ActivityRegisterBinding
import com.example.massive.util.customSharePreference

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var pref: customSharePreference
    private lateinit var google: GoogleSignInAuth
    private lateinit var fb: FacebookLoginAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = customSharePreference(this)
        google = GoogleSignInAuth(this, binding.pbSigninGoogle)
        google.initialize()
        fb = FacebookLoginAuth(this)
        fb.initialize()

        binding.etNamaLengkap.addTextChangedListener(loginTextWatcher)
        binding.etUsername.addTextChangedListener(loginTextWatcher)
        binding.etTglLahir.addTextChangedListener(loginTextWatcher)
        binding.etPassword.addTextChangedListener(loginTextWatcher)
        binding.etConfirmPassword.addTextChangedListener(loginTextWatcher)

        binding.btnRegister.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.btnGoogleRegist.setOnClickListener(this)
        binding.btnFacebookRegist.setOnClickListener(this)
    }

    private var loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val namaLengkap = binding.etNamaLengkap.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val tglLahir = binding.etTglLahir.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            binding.btnRegister.isEnabled = username.isNotEmpty() && password.isNotEmpty()
                    && namaLengkap.isNotEmpty() && tglLahir.isNotEmpty() && confirmPassword.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onClick(r: View) {
        when (r.id) {
            R.id.btn_register -> {
                val namaLengkap = binding.etNamaLengkap.text.toString()
                val username = binding.etUsername.text.toString()
                val tglLahir = binding.etTglLahir.text.toString()
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

            R.id.iv_back -> {
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_google_regist -> {
                pref.saveLogin(1).let {
                    binding.pbSigninGoogle.visibility = View.VISIBLE
                    google.signInGoogle()
                }
            }
            R.id.btn_facebook_regist -> {
                pref.saveLogin(1).let {
                    fb.loginFacebook()
                }
            }
        }
    }
}