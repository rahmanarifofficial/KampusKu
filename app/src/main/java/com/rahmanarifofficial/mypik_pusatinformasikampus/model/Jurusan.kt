package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Jurusan(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("jurusan")
    @Expose
    var jurusan: String? = null,
    @SerializedName("nama_kelompok")
    @Expose
    var namaKelompok: String? = null,
    @SerializedName("mapel")
    @Expose
    var mapel: String? = null,
    @SerializedName("tipe")
    @Expose
    var tipe: String? = null,
    @SerializedName("deskripsi")
    @Expose
    var deskripsi: String? = null,
    @SerializedName("isPopuler")
    @Expose
    var isPopuler: String? = null,
    @SerializedName("alasan_pilih")
    @Expose
    var alasanPilih: String? = null,
    @SerializedName("karakter_siswa")
    @Expose
    var karakterSiswa: String? = null,
    @SerializedName("prospek_kerja")
    @Expose
    var prospekKerja: String? = null,
    @SerializedName("profesi_kerja")
    @Expose
    var profesiKerja: String? = null,
    @SerializedName("id_kelompok")
    @Expose
    var idKelompok: String? = null,
    @SerializedName("link_banner")
    @Expose
    var fotoBanner: String? = null
)