package com.example.massive.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.massive.R
import com.example.massive.auth.FacebookLoginAuth
import com.example.massive.auth.GoogleSignInAuth
import com.example.massive.auth.UserRequest
import com.example.massive.databinding.ActivityLoginBinding
import com.example.massive.factory.UserViewModelFactory
import com.example.massive.repository.UserRepository
import com.example.massive.util.customSharePreference
import com.example.massive.viewModel.UserViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var pref: customSharePreference
    private lateinit var google: GoogleSignInAuth
    private lateinit var fb: FacebookLoginAuth
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setOnAction()
    }

    private fun init() {
        pref = customSharePreference(this@LoginActivity)
        google = GoogleSignInAuth(this, binding.pbSigninGoogle)
        google.initialize()
        fb = FacebookLoginAuth(this)
        fb.initialize()
        val viewModelFactory = UserViewModelFactory(UserRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    private fun setOnAction() {
        binding.etUsernameLogin.addTextChangedListener(loginTextWatcher)
        binding.etPasswordLogin.addTextChangedListener(loginTextWatcher)
        binding.btnMasuk.setOnClickListener(this)
        binding.ivBackLogin.setOnClickListener(this)
        binding.tvLupaPassword.setOnClickListener(this)
        binding.btnGoogleLogin.setOnClickListener(this)
        binding.btnFacebookLogin.setOnClickListener(this)
    }

    private var loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val username = binding.etUsernameLogin.text.toString().trim()
            val password = binding.etPasswordLogin.text.toString().trim()

            binding.btnMasuk.isEnabled = username.isNotEmpty() && password.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onClick(b: View) {
        when (b.id) {
            R.id.btn_masuk -> {
                val username = binding.etUsernameLogin.text.toString().trim()
                val password = binding.etPasswordLogin.text.toString().trim()
                val user = UserRequest(username, password)

                viewModel.loginUser(user)
                viewModel.token.observe(this, Observer {
                    if (it.isSuccessful) {
                        pref.saveLogin(1).let {
                            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Maaf, Login Gagal. \nPastikan Username dan Password Anda Telah Terdaftar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }

            R.id.iv_back_login -> {
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }

            R.id.tv_lupa_password -> {
                val intent = Intent(this@LoginActivity, LupaActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_google_login -> {
                pref.saveLogin(1).let {
                    binding.pbSigninGoogle.visibility = View.VISIBLE
                    google.signInGoogle()
                }
            }

            R.id.btn_facebook_login -> {
                pref.saveLogin(1).let {
                    fb.loginFacebook()
                }
            }
        }
    }
}
