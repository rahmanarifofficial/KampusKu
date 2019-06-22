package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan

interface DetailJurusanView {
    fun showLoading()
    fun hideLoading()
    fun showJurusan(data: List<Jurusan>)
    fun showError(data: String)
}