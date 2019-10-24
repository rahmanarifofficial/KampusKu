package com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN

interface DetailKampusView {
    fun showPtn(data: List<PTN>?)
    fun showError(error: String?)
}