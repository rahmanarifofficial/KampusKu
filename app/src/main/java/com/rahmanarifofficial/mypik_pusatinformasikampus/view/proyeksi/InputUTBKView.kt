package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Nilai
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi

interface InputUTBKView {
    fun showProgress()
    fun hideProgress()
    fun showPTNList(data: List<PTN>)
    fun showProdiList(data: List<Prodi>, kode: Int)
    fun showError(data: String)
    fun showNilai(data: List<Nilai>)
}