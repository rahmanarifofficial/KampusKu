package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan

interface TrendView {
    fun showLoading()
    fun hideLoading()
    fun showPopulerJurusan(data: List<Jurusan>)
    fun showJurusanList(data: List<Jurusan>)
    fun showError(data: String)
}