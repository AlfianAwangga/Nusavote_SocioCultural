package com.example.massive.view.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.massive.R
import com.example.massive.auth.FacebookLoginAuth
import com.example.massive.auth.GoogleSignInAuth
import com.example.massive.databinding.ActivityEditProfileBinding
import com.example.massive.util.customSharePreference
import com.example.massive.view.fragments.ProfileFragment

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var pref: customSharePreference
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
        binding.btnSimpanProfile.setOnClickListener {
            val intent = Intent()
            intent.putExtra("key", 1)
            setResult(Activity.RESULT_OK, intent)
            finish()
            Toast.makeText(this, "Data Telah Tersimpan!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun dataUserEditText() {
        val bundle = intent.extras
        binding.etNamaProfile.setText(bundle?.getString("nama"))
        binding.etUsernameProfile.setText(bundle?.getString("username"))

    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            val message: String? = "Apakah Anda yakin ingin Keluar?"
            showCustomDialog(message)
        }
    }

    private fun showCustomDialog(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_custom)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessageDialog: TextView = dialog.findViewById(R.id.tv_dialog_message)
        val btnYesDialog: Button = dialog.findViewById(R.id.btn_dialog_yes)
        val btnNoDialog: Button = dialog.findViewById(R.id.btn_dialog_no)

        tvMessageDialog.text = message

        btnYesDialog.setOnClickListener {
            pref.saveLogin(0).let {
                google.signOutGoogle()
                fb.logoutFacebook()
                val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        btnNoDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun init() {
        pref = customSharePreference(this@EditProfileActivity)
        google = GoogleSignInAuth(this, binding.btnSimpanProfile)
        google.initialize()
        fb = FacebookLoginAuth(this)
        fb.initialize()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_pilih_file -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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