package com.rahmanarifofficial.mypik_pusatinformasikampus.network

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("index.php/api/universitas/ptn")
    fun getListPTN(): Call<List<PTN>>

    @GET("universitas/ptn/{kode}")
    fun getDetailPTN(@Path("kode") kode: String): Call<PTN>
}