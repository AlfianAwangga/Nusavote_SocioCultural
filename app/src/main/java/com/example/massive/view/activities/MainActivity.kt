package com.example.massive.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.massive.R
import com.example.massive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun splashScreen() {
        Thread.sleep(1000)
        installSplashScreen()
    }
}