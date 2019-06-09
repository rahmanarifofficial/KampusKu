package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.detailkampus.DetailKampusView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.KampusView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.detailkampus.ProdiKampusView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        fun getSearchPTN(view: KampusView, nama: String) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getSearchPTN(nama)
            call?.enqueue(object : Callback<List<PTN>> {
                override fun onFailure(call: Call<List<PTN>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<PTN>>, response: Response<List<PTN>>) {
                    view.showPtnList(response.body())
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

        fun getDetailProdi(view: ProdiKampusView, kode: String) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getDetailProdi(kode)
            call?.enqueue(object : Callback<List<Prodi>> {
                override fun onResponse(call: Call<List<Prodi>>, response: Response<List<Prodi>>) {
                    view.showDetailProdi(response.body()!!)
                }

                override fun onFailure(call: Call<List<Prodi>>, t: Throwable) {
                    view.showError(t.message!!)
                }
            })
        }
    }
}