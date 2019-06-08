package com.rahmanarifofficial.mypik_pusatinformasikampus.util

import android.content.Context
import android.content.SharedPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.R

class AuthPreferences(val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setSaveEmail(input: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        val key = context.getResources().getString(R.string.email_text)
        editor.putString(key, input)
        editor.commit()
    }

    fun getEmail(): String {
        val key = context.getResources().getString(R.string.email_text)
        return prefs.getString(key, null)
    }

    fun setSavePassword(input: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        val key = context.getResources().getString(R.string.daya_tampung_text)
        editor.putString(key, input)
        editor.commit()
    }

    fun getPassword(): String {
        val key = context.getResources().getString(R.string.daya_tampung_text)
        return prefs.getString(key, null)
    }
}