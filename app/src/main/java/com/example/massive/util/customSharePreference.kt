package com.example.massive.util

import android.content.Context
import android.content.SharedPreferences

class customSharePreference(val  context: Context) {

    private val prefs : SharedPreferences = context.getSharedPreferences("Simpan", Context.MODE_PRIVATE)

    fun saveLogin(login : Int){
        val editor = prefs.edit()
        editor.putInt("SaveLogin", login)
        editor.apply()
    }

    fun getLogin () : Int = prefs.getInt("SaveLogin", 0)
}