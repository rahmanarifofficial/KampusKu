package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi.InputUTBKView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyeksiPresenter {
    companion object {
        fun getListPTN(view: InputUTBKView) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getListPTN()
            call?.enqueue(object : Callback<List<PTN>> {
                override fun onFailure(call: Call<List<PTN>>, t: Throwable) {
                    view.showError(t.message!!)
                }

                override fun onResponse(call: Call<List<PTN>>, response: Response<List<PTN>>) {
                    view.showPTNList(response.body()!!)
                }
            })
        }

        fun getProdiByPTN(view: InputUTBKView, kode: String) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getProdiListByPTN(kode)
            call?.enqueue(object : Callback<List<Prodi>> {
                override fun onFailure(call: Call<List<Prodi>>, t: Throwable) {
                    view.showError(t.message!!)
                }

                override fun onResponse(call: Call<List<Prodi>>, response: Response<List<Prodi>>) {
                    view.showProdiList(response.body())
                }
            })
        }
    }
}