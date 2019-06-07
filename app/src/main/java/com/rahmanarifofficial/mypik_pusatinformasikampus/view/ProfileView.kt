package com.rahmanarifofficial.mypik_pusatinformasikampus.view

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna

interface ProfileView {
    fun showProgress()
    fun hideProgress()
    fun showProfil(data: List<Pengguna>)
    fun showToast(data: String)
}