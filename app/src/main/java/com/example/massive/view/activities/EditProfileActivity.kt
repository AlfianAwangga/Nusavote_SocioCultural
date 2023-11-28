package com.example.massive.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.massive.R
import com.example.massive.databinding.ActivityEditProfileBinding
import com.example.massive.util.customSharePreference


private lateinit var binding: ActivityEditProfileBinding
private lateinit var pref : customSharePreference
private var PickImageReq = 1 // Konstanta untuk kode permintaan pemilihan gambar

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
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
                val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun init(){
        pref = customSharePreference(this@EditProfileActivity)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_pilih_file -> {
                val intent =Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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
}