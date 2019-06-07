package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import com.google.firebase.auth.FirebaseUser

interface AkunView {
    fun showToast(data: String)
    fun showProgress()
    fun hideProgress()
    fun updateUI(user: FirebaseUser, type: Int)
}