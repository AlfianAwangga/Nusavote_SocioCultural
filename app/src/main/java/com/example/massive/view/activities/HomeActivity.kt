package com.example.massive.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.massive.R
import com.example.massive.databinding.ActivityHomeBinding
import com.example.massive.view.fragments.GuideFragment
import com.example.massive.view.fragments.HomeFragment
import com.example.massive.view.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chipNavigation()
//        bottomNavigation()
    }

//    private fun bottomNavigation() {
//        val navController = findNavController(R.id.fragmentContainerView)
//        binding.bottomNavigationView.setupWithNavController(navController)
//    }

    private fun chipNavigation() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setOnItemSelectedListener{ id ->
            when(id) {
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.guideFragment -> navController.navigate(R.id.guideFragment)
                R.id.profileFragment -> navController.navigate(R.id.profileFragment)
            }
        }
    }
}