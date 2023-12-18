package com.example.massive.view.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.massive.R
import com.example.massive.auth.FacebookLoginAuth
import com.example.massive.auth.GoogleSignInAuth
import com.example.massive.databinding.ActivityEditProfileBinding
import com.example.massive.util.customSharePreference

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var pref : customSharePreference
    private var PickImageReq = 1 // Konstanta untuk kode permintaan pemilihan gambar
    private lateinit var google: GoogleSignInAuth
    private lateinit var fb: FacebookLoginAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPilihFile.setOnClickListener(this)
        binding.ivBackProfile.setOnClickListener(this)

        init()
        dataUserEditText()
        logout()
        simpan()

    }

    private fun simpan() {
        binding.btnSimpanProfile.setOnClickListener{
            val intent = Intent()
            intent.putExtra("key", 1)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun dataUserEditText() {
        val bundle = intent.extras
        binding.etNamaProfile.setText(bundle?.getString("nama"))
        binding.etEmailProfile.setText(bundle?.getString("email"))

    }
    private fun logout() {
        binding.btnLogout.setOnClickListener{
            pref.saveLogin(0).let {
                google.signOutGoogle()
                fb.logoutFacebook()
                val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun init(){
        pref = customSharePreference(this@EditProfileActivity)
        google = GoogleSignInAuth(this, binding.btnSimpanProfile)
        google.initialize()
        fb = FacebookLoginAuth(this)
        fb.initialize()
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_pilih_file -> {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, PickImageReq)

            }
            R.id.iv_back_profile -> {
                val intent = Intent()
                intent.putExtra("key", 1)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PickImageReq && resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            binding.imgProfileEdit.setImageURI(selectedImage)
            val requestOptions = RequestOptions().transform(CircleCrop())
            Glide.with(this)
                .load(selectedImage)
                .apply(requestOptions)
                .into(binding.imgProfileEdit)

        }
    }
}