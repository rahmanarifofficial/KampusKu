package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend.DetailJurusanView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend.TrendView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendPresenter() {
    companion object {
        fun showPopulerJurusan(view: TrendView) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getPopulerJurusan()
            call?.enqueue(object : Callback<List<Jurusan>> {
                override fun onResponse(call: Call<List<Jurusan>>, response: Response<List<Jurusan>>) {
                    view.showPopulerJurusan(response.body())
                    view.hideLoading()
                }

                override fun onFailure(call: Call<List<Jurusan>>, t: Throwable) {
                    view.showError(t.message)
                    view.hideLoading()
                }
            })
        }

        fun getListJurusan(id: Int, view: TrendView) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getJurusanList(id)
            call?.enqueue(object : Callback<List<Jurusan>> {
                override fun onFailure(call: Call<List<Jurusan>>, t: Throwable) {
                    view.showError(t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Jurusan>>, response: Response<List<Jurusan>>) {
                    view.showJurusanList(response.body())
                    view.hideLoading()
                }
            })
        }

        fun searchJurusan(query: String, view: TrendView) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getSearchJurusan(query)
            call?.enqueue(object : Callback<List<Jurusan>> {
                override fun onFailure(call: Call<List<Jurusan>>, t: Throwable) {
                    view.showError(t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Jurusan>>, response: Response<List<Jurusan>>) {
                    view.showJurusanList(response.body())
                    view.hideLoading()
                }
            })
        }

        fun showDetailJurusan(id: String, view: DetailJurusanView) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getDetailJurusan(id)
            call?.enqueue(object : Callback<List<Jurusan>> {
                override fun onFailure(call: Call<List<Jurusan>>, t: Throwable) {
                    view.showError(t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Jurusan>>, response: Response<List<Jurusan>>) {
                    view.showJurusan(response.body())
                    view.hideLoading()
                }
            })
        }

        fun showJurusanPTN(nama:String, view: DetailJurusanView){
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getProdiListByName(nama)
            call?.enqueue(object : Callback<List<Prodi>>{
                override fun onFailure(call: Call<List<Prodi>>, t: Throwable) {
                    view.showError(t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Prodi>>, response: Response<List<Prodi>>) {
                    view.showUniv(response.body())
                    view.hideLoading()
                }
            })
        }
    }
}