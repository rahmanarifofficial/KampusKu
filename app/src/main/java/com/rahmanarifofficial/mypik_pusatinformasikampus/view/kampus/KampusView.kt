package com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN

interface KampusView {
    fun showLoading()
    fun hideLoading()
    fun showPtnList(data: List<PTN>?)
    fun showError()
}