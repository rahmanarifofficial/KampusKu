package com.rahmanarifofficial.mypik_pusatinformasikampus.network

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("index.php/api/universitas/ptn")
    fun getListPTN(): Call<List<PTN>>

    @GET("index.php/api/universitas/ptn")
    fun getDetailPTN(@Query("kode") kode: String): Call<List<PTN>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getProdiList(@Query("kode_ptn") kode: String, @Query("tipe") tipe: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getDetailProdi(@Query("kode_prodi") kode: String): Call<List<Prodi>>
}