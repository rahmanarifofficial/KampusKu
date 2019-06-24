package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Nilai(
    @SerializedName("kode_nilai")
    @Expose
    var kodeNilai: String? = null,
    @SerializedName("id_pengguna")
    @Expose
    var idPengguna: String? = null,
    @SerializedName("nilai_penalaran_umum")
    @Expose
    var nilaiPenalaranUmum: String? = null,
    @SerializedName("nilai_pengetahuan_kuantitatif")
    @Expose
    var nilaiPengetahuanKuantitatif: String? = null,
    @SerializedName("nilai_pengetahuan_umum")
    @Expose
    var nilaiPengetahuanUmum: String? = null,
    @SerializedName("nilai_memahami_bacaan")
    @Expose
    var nilaiMemahamiBacaan: String? = null,
    @SerializedName("nilai_mat_saintek")
    @Expose
    var nilaiMatSaintek: String? = null,
    @SerializedName("nilai_fisika")
    @Expose
    var nilaiFisika: String? = null,
    @SerializedName("nilai_kimia")
    @Expose
    var nilaiKimia: String? = null,
    @SerializedName("nilai_biologi")
    @Expose
    var nilaiBiologi: String? = null,
    @SerializedName("nilai_mat_soshum")
    @Expose
    var nilaiMatSoshum: String? = null,
    @SerializedName("nilai_geografi")
    @Expose
    var nilaiGeografi: String? = null,
    @SerializedName("nilai_sejarah")
    @Expose
    var nilaiSejarah: String? = null,
    @SerializedName("nilai_sosiologi")
    @Expose
    var nilaiSosiologi: String? = null,
    @SerializedName("nilai_ekonomi")
    @Expose
    var nilaiEkonomi: String? = null,
    @SerializedName("nama_ptn")
    @Expose
    var namaPtn: String? = null,
    @SerializedName("nama_prodi")
    @Expose
    var namaProdi: String? = null
)