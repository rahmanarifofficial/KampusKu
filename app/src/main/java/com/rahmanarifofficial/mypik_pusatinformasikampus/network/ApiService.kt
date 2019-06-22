package com.rahmanarifofficial.mypik_pusatinformasikampus.network

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("index.php/api/universitas/ptn")
    fun getListPTN(): Call<List<PTN>>

    @GET("index.php/api/universitas/ptn")
    fun getDetailPTN(@Query("kode") kode: String): Call<List<PTN>>

    @GET("index.php/api/universitas/search_ptn")
    fun getSearchPTN(@Query("nama") nama: String): Call<List<PTN>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getProdiList(@Query("kode_ptn") kode: String, @Query("tipe") tipe: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getProdiListByPTN(@Query("kode_ptn") kode: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getDetailProdi(@Query("kode_prodi") kode: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/jurusan?isPopuler=true")
    fun getPopulerJurusan(): Call<List<Jurusan>>

    @GET("index.php/api/universitas/detail_jurusan/{id}")
    fun getDetailJurusan(@Path("id") id: String): Call<List<Jurusan>>

    @POST("index.php/api/universitas/pengguna")
    @FormUrlEncoded
    fun insertPengguna(
        @Field("nama_pengguna") namaPengguna: String,
        @Field("email_pengguna") emailPengguna: String,
        @Field("password_pengguna") passwordPengguna: String,
        @Field("alamat_pengguna") alamatPengguna: String,
        @Field("no_telp") noTelp: String,
        @Field("asal_sekolah") asalSekolah: String,
        @Field("instagram") instagram: String?,
        @Field("facebook") facebook: String?,
        @Field("link_foto") link_foto: String?
    ): Call<Pengguna>

    @GET("index.php/api/universitas/pengguna")
    fun getPengguna(
        @Query("email_pengguna") emailPengguna: String,
        @Query("password_pengguna") passwordPengguna: String
    ): Call<List<Pengguna>>
}