package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Beasiswa(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("beasiswa")
    @Expose
    var beasiswa: String? = null,
    @SerializedName("jenis_pembiayaan")
    @Expose
    var jenisPembiayaan: String? = null,
    @SerializedName("deadline")
    @Expose
    var deadline: String? = null,
    @SerializedName("kategori")
    @Expose
    var kategori: String? = null,
    @SerializedName("deskripsi")
    @Expose
    var deskripsi: String? = null,
    @SerializedName("komponen_beasiswa")
    @Expose
    var komponenBeasiswa: String? = null,
    @SerializedName("persyaratan_pendaftar")
    @Expose
    var persyaratanPendaftar: String? = null,
    @SerializedName("berkas_pendaftaran")
    @Expose
    var berkasPendaftaran: String? = null,
    @SerializedName("link_banner")
    @Expose
    var linkBanner: String? = null,
    @SerializedName("penyelenggara")
    @Expose
    var penyelenggara: String? = null,
    @SerializedName("proses_pendaftaran")
    @Expose
    var prosesPendaftaran: String? = null
)