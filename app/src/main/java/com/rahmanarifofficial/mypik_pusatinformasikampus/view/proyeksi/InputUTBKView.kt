package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi

interface InputUTBKView {
    fun showPTNList(data: List<PTN>?)
    fun showProdiList(data: List<Prodi>?)
    fun showError(data: String)
}