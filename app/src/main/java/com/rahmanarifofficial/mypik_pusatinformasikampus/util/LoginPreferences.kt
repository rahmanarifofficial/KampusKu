package com.rahmanarifofficial.mypik_pusatinformasikampus.util

import android.content.Context
import android.content.SharedPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.R

class LoginPreferences(val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setWasLogin(input: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit()
        val key = context.getResources().getString(R.string.app_login)
        editor.putBoolean(key, input)
        editor.commit()
    }

    fun getWasLogin(): Boolean {
        val key = context.getResources().getString(R.string.app_login)
        return prefs.getBoolean(key, false)
    }
}