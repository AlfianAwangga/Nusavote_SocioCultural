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
        val avdSelamatRatong = getDrawable(R.drawable.avd_selamat_ratong) as AnimatedVectorDrawable
        val avdRahajengRauh = getDrawable(R.drawable.avd_rahajeng_rauh) as AnimatedVectorDrawable
        val avdSugengRawuh = getDrawable(R.drawable.avd_sugeng_rawuh) as AnimatedVectorDrawable
        val avdTabeaWaya = getDrawable(R.drawable.avd_tabea_waya) as AnimatedVectorDrawable
        val avdWilujengSumping = getDrawable(R.drawable.avd_wilujeng_sumping) as AnimatedVectorDrawable
        val avdSaleumTeuka = getDrawable(R.drawable.avd_saleum_teuka) as AnimatedVectorDrawable
        val avdSalamaikDatang = getDrawable(R.drawable.avd_salamaik_datang) as AnimatedVectorDrawable
        val avdSalamatDatang = getDrawable(R.drawable.avd_salamat_datang) as AnimatedVectorDrawable
        val avdHoras = getDrawable(R.drawable.avd_horas) as AnimatedVectorDrawable
        val avdOnomiFakhai = getDrawable(R.drawable.avd_onomi_fakhai) as AnimatedVectorDrawable
        val avdKopisanangan = getDrawable(R.drawable.avd_kopisanangan) as AnimatedVectorDrawable
        val avdSalamaEngka = getDrawable(R.drawable.avd_salama_engka) as AnimatedVectorDrawable
        val avdKoePontam = getDrawable(R.drawable.avd_koe_pontam) as AnimatedVectorDrawable
        val avdSalamakki = getDrawable(R.drawable.avd_salamakki_kabattuanta) as AnimatedVectorDrawable

        binding.ivWelcome.setImageDrawable(avdWelcome)
        binding.ivSelamatRatong.setImageDrawable(avdSelamatRatong)
        binding.ivRahajengRauh.setImageDrawable(avdRahajengRauh)
        binding.ivSugengRawuh.setImageDrawable(avdSugengRawuh)
        binding.ivTabeaWaya.setImageDrawable(avdTabeaWaya)
        binding.ivWilujengSumping.setImageDrawable(avdWilujengSumping)
        binding.ivSaleumTeuka.setImageDrawable(avdSaleumTeuka)
        binding.ivSalamaikDatang.setImageDrawable(avdSalamaikDatang)
        binding.ivSalamatDatang.setImageDrawable(avdSalamatDatang)
        binding.ivHoras.setImageDrawable(avdHoras)
        binding.ivOnomiFakhai.setImageDrawable(avdOnomiFakhai)
        binding.ivKopisanangan.setImageDrawable(avdKopisanangan)
        binding.ivSalamaEngka.setImageDrawable(avdSalamaEngka)
        binding.ivKoePontam.setImageDrawable(avdKoePontam)
        binding.ivSalamakki.setImageDrawable(avdSalamakki)

        avdWelcome.start()
        avdSelamatRatong.start()
        avdRahajengRauh.start()
        avdSugengRawuh.start()
        avdTabeaWaya.start()
        avdWilujengSumping.start()
        avdSaleumTeuka.start()
        avdSalamaikDatang.start()
        avdSalamatDatang.start()
        avdHoras.start()
        avdOnomiFakhai.start()
        avdKopisanangan.start()
        avdSalamaEngka.start()
        avdKoePontam.start()
        avdSalamakki.start()
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