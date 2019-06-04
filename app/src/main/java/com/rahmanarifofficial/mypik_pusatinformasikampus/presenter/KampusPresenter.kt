package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.DetailKampusView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.KampusView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.ProdiKampusView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class KampusPresenter() {
    companion object {
        fun getListPTN(view: KampusView) {
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

        fun getPTN(view: DetailKampusView, kode: String) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getDetailPTN(kode)
            call?.enqueue(object : Callback<List<PTN>> {
                override fun onFailure(call: Call<List<PTN>>, t: Throwable) {
                    view.showError(t.message!!)
                }

                override fun onResponse(call: Call<List<PTN>>, response: Response<List<PTN>>) {
                    view.showPtn(response.body()!!)
                }
            })
        }

        fun getProdiList(view: ProdiKampusView, kode: String, tipe: String) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getProdiList(kode, tipe)
            call?.enqueue(object : Callback<List<Prodi>> {
                override fun onResponse(call: Call<List<Prodi>>, response: Response<List<Prodi>>) {
                    view.showProdi(response.body()!!)
                }

                override fun onFailure(call: Call<List<Prodi>>, t: Throwable) {
                    view.showError(t.message!!)
                }
            })
        }
    }
}