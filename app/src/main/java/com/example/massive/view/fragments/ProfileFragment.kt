package com.example.massive.view.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.massive.R
import com.example.massive.databinding.FragmentProfileBinding
import com.example.massive.util.customSharePreference
import com.example.massive.view.activities.EditProfileActivity
import com.example.massive.view.activities.HomeActivity
import com.example.massive.view.activities.MainActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref : customSharePreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        init()

        editProfil()

        return binding.root
    }


    private fun editProfil() {
        binding.btnEditProfile.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("nama", this.binding.tvNamaUser.text.toString())
            bundle.putString("email", this.binding.tvEmailUser.text.toString())

            val intent = Intent(activity, EditProfileActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun init() {
        pref =  customSharePreference(requireContext())
    }


}