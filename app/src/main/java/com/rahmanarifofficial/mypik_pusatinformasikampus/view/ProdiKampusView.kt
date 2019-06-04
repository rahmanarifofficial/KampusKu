package com.rahmanarifofficial.mypik_pusatinformasikampus.view

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi

interface ProdiKampusView {
    fun showProdi(data: List<Prodi>)
    fun showError(data: String)
}