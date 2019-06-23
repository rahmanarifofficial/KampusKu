package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa.BeasiswaView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeasiswaPresenter {
    companion object {
        fun showBeasiswa(view: BeasiswaView) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getBeasiswaList()
            call?.enqueue(object : Callback<List<Beasiswa>> {
                override fun onFailure(call: Call<List<Beasiswa>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Beasiswa>>, response: Response<List<Beasiswa>>) {
                    view.showBeasiswa(response.body()!!)
                    view.hideLoading()
                }
            })
        }

        fun searchBeasiswa(view: BeasiswaView, query: String) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getSearchBeasiswa(query)
            call?.enqueue(object : Callback<List<Beasiswa>> {
                override fun onFailure(call: Call<List<Beasiswa>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Beasiswa>>, response: Response<List<Beasiswa>>) {
                    view.showBeasiswa(response.body()!!)
                    view.hideLoading()
                }
            })
        }

        fun showDetailBeasiswa(view: BeasiswaView, id: String) {
            view.showLoading()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getDetailBeasiswa(id)
            call?.enqueue(object : Callback<List<Beasiswa>> {
                override fun onFailure(call: Call<List<Beasiswa>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<List<Beasiswa>>, response: Response<List<Beasiswa>>) {
                    view.showBeasiswa(response.body()!!)
                    view.hideLoading()
                }
            })
        }
    }
}