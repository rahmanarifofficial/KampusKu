package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi

interface DetailJurusanView {
    fun showLoading()
    fun hideLoading()
    fun showJurusan(data: List<Jurusan>?)
    fun showUniv(data: List<Prodi>?)
    fun showError(data: String?)
}