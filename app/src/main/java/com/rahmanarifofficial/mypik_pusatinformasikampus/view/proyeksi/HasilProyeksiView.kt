package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Nilai

interface HasilProyeksiView {
    fun showProgress()
    fun hideProgress()
    fun showError(data: String)
    fun showNilai(data: List<Nilai>)
}