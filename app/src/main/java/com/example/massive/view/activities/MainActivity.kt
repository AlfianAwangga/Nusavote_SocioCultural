package com.example.massive.view.activities

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.massive.R
import com.example.massive.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        welcomeAnimation()

        binding.btnMulai.setOnClickListener(this)
        binding.btnPunyaAkun.setOnClickListener(this)

    }

    private fun splashScreen() {
        Thread.sleep(1000)
        installSplashScreen()
    }

    private fun welcomeAnimation() {
        val avdWelcome = getDrawable(R.drawable.avd_welcome) as AnimatedVectorDrawable
        binding.ivWelcome.setImageDrawable(avdWelcome)
        avdWelcome.start()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_mulai -> {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_punyaAkun -> {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}