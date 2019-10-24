package com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa

interface DetailBeasiswaView {
    fun showLoading()
    fun hideLoading()
    fun showBeasiswa(data: List<Beasiswa>?)
    fun showError(data: String?)
}