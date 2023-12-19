package com.example.massive.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.massive.R
import com.example.massive.auth.FacebookLoginAuth
import com.example.massive.auth.GoogleSignInAuth
import com.example.massive.databinding.ActivityRegisterBinding
import com.example.massive.factory.UserViewModelFactory
import com.example.massive.model.UserModel
import com.example.massive.repository.UserRepository
import com.example.massive.util.customSharePreference
import com.example.massive.viewModel.UserViewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var pref: customSharePreference
    private lateinit var google: GoogleSignInAuth
    private lateinit var fb: FacebookLoginAuth
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setOnAction()
        setOnAction()
    }

    private fun init() {
        pref = customSharePreference(this)
        google = GoogleSignInAuth(this, binding.pbSigninGoogle)
        google.initialize()
        fb = FacebookLoginAuth(this)
        fb.initialize()
        val viewModelFactory = UserViewModelFactory(UserRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    private fun setOnAction() {
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
                    viewModel.addUser(
                        UserModel(
                            tglLahir,
                            namaLengkap,
                            username,
                            "user",
                            confirmPassword,
                            password
                        )
                    )
                    viewModel.users.observe(this, Observer {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }
            }

            R.id.iv_back -> {
                setResult(Activity.RESULT_OK, Intent())
                finish()
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