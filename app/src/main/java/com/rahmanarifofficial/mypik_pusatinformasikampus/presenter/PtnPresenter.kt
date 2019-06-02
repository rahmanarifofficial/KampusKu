package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.PtnView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PtnPresenter() {
    companion object {
        fun getListPTN(view: PtnView) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getListPTN()
            call?.enqueue(object : Callback<List<PTN>> {
                override fun onFailure(call: Call<List<PTN>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<PTN>>, response: Response<List<PTN>>) {
                    view.showPtnList(response.body()!!)
                    view.hideLoading()
                }
            })
        }
    }
}