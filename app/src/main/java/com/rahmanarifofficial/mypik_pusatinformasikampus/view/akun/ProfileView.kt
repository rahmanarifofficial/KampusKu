package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna

interface ProfileView {
    fun showProfil(data: List<Pengguna>)
    fun showToast(data: String)
    fun updateUI()
}