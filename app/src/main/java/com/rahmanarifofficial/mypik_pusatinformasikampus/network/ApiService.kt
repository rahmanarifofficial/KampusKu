package com.rahmanarifofficial.mypik_pusatinformasikampus.network

import com.rahmanarifofficial.mypik_pusatinformasikampus.model.*
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
    fun getProdiListByPTN(@Query("nama_universitas") kode: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getProdiListByName(@Query("nama") nama: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getProdiListByNameByUniv(@Query("nama") nama: String, @Query("nama_universitas") nama_univ: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/prodi_ptn")
    fun getDetailProdi(@Query("kode_prodi") kode: String): Call<List<Prodi>>

    @GET("index.php/api/universitas/jurusan?isPopuler=true")
    fun getPopulerJurusan(): Call<List<Jurusan>>

    @GET("index.php/api/universitas/jurusan")
    fun getJurusanList(@Query("id_kelompok") id_kelompok: Int): Call<List<Jurusan>>

    @GET("index.php/api/universitas/detail_jurusan/{id}")
    fun getDetailJurusan(@Path("id") id: String): Call<List<Jurusan>>

    @GET("index.php/api/universitas/jurusan")
    fun getSearchJurusan(@Query("jurusan") jurusan: String): Call<List<Jurusan>>

    @GET("index.php/api/universitas/beasiswa")
    fun getBeasiswaList(): Call<List<Beasiswa>>

    @GET("index.php/api/universitas/search_beasiswa")
    fun getSearchBeasiswa(@Query("beasiswa") beasiswa: String): Call<List<Beasiswa>>

    @GET("index.php/api/universitas/detail_beasiswa/{id}")
    fun getDetailBeasiswa(@Path("id") id: String): Call<List<Beasiswa>>

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

    @POST("index.php/api/nilai/nilai_sbm")
    @FormUrlEncoded
    fun insertNilai(
        @Field("id_pengguna") idPengguna: String,
        @Field("nilai_penalaran_umum") nilai_penalaran_umum: String,
        @Field("nilai_pengetahuan_kuantitatif") nilai_pengetahuan_kuantitatif: String,
        @Field("nilai_pengetahuan_umum") nilai_pengetahuan_umum: String,
        @Field("nilai_memahami_bacaan") nilai_memahami_bacaan: String,
        @Field("nilai_mat_saintek") nilai_mat_saintek: String?,
        @Field("nilai_fisika") nilai_fisika: String?,
        @Field("nilai_kimia") nilai_kimia: String?,
        @Field("nilai_biologi") nilai_biologi: String?,
        @Field("nilai_mat_soshum") nilai_mat_soshum: String?,
        @Field("nilai_geografi") nilai_geografi: String?,
        @Field("nilai_sejarah") nilai_sejarah: String?,
        @Field("nilai_sosiologi") nilai_sosiologi: String?,
        @Field("nilai_ekonomi") nilai_ekonomi: String?,
        @Field("nama_ptn") nama_ptn: String,
        @Field("nama_prodi") nama_prodi: String
    ): Call<Nilai>

    @GET("index.php/api/nilai/nilai_sbm")
    fun getNilaiPengguna(
        @Query("id_pengguna") idPengguna: String
    ): Call<List<Nilai>>

}