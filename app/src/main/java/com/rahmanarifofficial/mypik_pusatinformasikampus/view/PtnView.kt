package com.rahmanarifofficial.mypik_pusatinformasikampus.view

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN

interface PtnView {
    fun showLoading()
    fun hideLoading()
    fun showPtnList(data: List<PTN>)
    fun showError(error: String)
}