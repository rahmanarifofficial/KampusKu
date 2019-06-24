package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Nilai
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi.HasilProyeksiView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi.InputUTBKView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

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
            view.showProgress()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getProdiListByPTN(kode)
            call?.enqueue(object : Callback<List<Prodi>> {
                override fun onFailure(call: Call<List<Prodi>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideProgress()
                }

                override fun onResponse(call: Call<List<Prodi>>, response: Response<List<Prodi>>) {
                    view.showProdiList(response.body()!!, 1)
                    view.hideProgress()
                }
            })
        }

        fun insertNilai(
            view: InputUTBKView,
            idPengguna: String,
            nilai_penalaran_umum: String,
            nilai_pengetahuan_kuantitatif: String,
            nilai_pengetahuan_umum: String,
            nilai_memahami_bacaan: String,
            nilai_mat_saintek: String?,
            nilai_fisika: String?,
            nilai_kimia: String?,
            nilai_biologi: String?,
            nilai_mat_soshum: String?,
            nilai_geografi: String?,
            nilai_sejarah: String?,
            nilai_sosiologi: String?,
            nilai_ekonomi: String?,
            nama_ptn: String,
            nama_prodi: String
        ) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.insertNilai(
                idPengguna,
                nilai_penalaran_umum,
                nilai_pengetahuan_kuantitatif,
                nilai_pengetahuan_umum,
                nilai_memahami_bacaan,
                nilai_mat_saintek,
                nilai_fisika,
                nilai_kimia,
                nilai_biologi,
                nilai_mat_soshum,
                nilai_geografi,
                nilai_sejarah,
                nilai_sosiologi,
                nilai_ekonomi,
                nama_ptn,
                nama_prodi
            )
            call?.enqueue(object : Callback<Nilai> {
                override fun onFailure(call: Call<Nilai>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideProgress()
                }

                override fun onResponse(call: Call<Nilai>, response: Response<Nilai>) {
                    view.hideProgress()
                }
            })
        }

        fun getNilai(view: InputUTBKView, idPengguna: String) {
            view.showProgress()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getNilaiPengguna(idPengguna)
            call?.enqueue(object : Callback<List<Nilai>> {
                override fun onFailure(call: Call<List<Nilai>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideProgress()
                }

                override fun onResponse(call: Call<List<Nilai>>, response: Response<List<Nilai>>) {
                    view.showNilai(response.body()!!)
                    view.hideProgress()
                }
            })
        }

        fun getKodeProdi(view: InputUTBKView, nama_ptn: String, nama_prodi: String) {
            view.showProgress()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getProdiListByNameByUniv(nama_prodi, nama_ptn)
            call?.enqueue(object : Callback<List<Prodi>> {
                override fun onFailure(call: Call<List<Prodi>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideProgress()
                }

                override fun onResponse(call: Call<List<Prodi>>, response: Response<List<Prodi>>) {
                    view.showProdiList(response.body()!!, 2)
                    view.hideProgress()
                }
            })
        }

        fun getNilai(view: HasilProyeksiView, idPengguna: String) {
            view.showProgress()
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getNilaiPengguna(idPengguna)
            call?.enqueue(object : Callback<List<Nilai>> {
                override fun onFailure(call: Call<List<Nilai>>, t: Throwable) {
                    view.showError(t.message!!)
                    view.hideProgress()
                }

                override fun onResponse(call: Call<List<Nilai>>, response: Response<List<Nilai>>) {
                    view.showNilai(response.body()!!)
                    view.hideProgress()
                }
            })
        }
    }
}