package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PTN {
    @SerializedName("KODE")
    @Expose
    private var kode: String? = null
    @SerializedName("NAMA")
    @Expose
    private var nama: String? = null
    @SerializedName("LINK_PMB")
    @Expose
    private var link_pmb: String? = null
    @SerializedName("LINK_LOGO")
    @Expose
    private var link_logo: String? = null
    @SerializedName("AKREDITASI")
    @Expose
    private var akreditasi: String? = null
    @SerializedName("PROVINSI")
    @Expose
    private var provinsi: String? = null
    @SerializedName("WORLD_RANK")
    @Expose
    private var worldrank: String? = null
    @SerializedName("JUMLAH_PRODI")
    @Expose
    private var jumlah_prodi: String? = null
    @SerializedName("LINK_DROP")
    @Expose
    private var link_drop: String? = null

    constructor(
        kode: String?,
        nama: String?,
        link_pmb: String?,
        link_logo: String?,
        akreditasi: String?,
        provinsi: String?,
        worldrank: String?,
        jumlah_prodi: String?,
        link_drop: String?
    ) {
        this.kode = kode
        this.nama = nama
        this.link_pmb = link_pmb
        this.link_logo = link_logo
        this.akreditasi = akreditasi
        this.provinsi = provinsi
        this.worldrank = worldrank
        this.jumlah_prodi = jumlah_prodi
        this.link_drop = link_drop
    }


    fun getKODE(): String? {
        return kode
    }

    fun getNAMA(): String? {
        return nama
    }

    fun getLINKPMB(): String? {
        return link_pmb
    }

    fun getLINKLOGO(): String? {
        return link_logo
    }

    fun getAKREDITASI(): String? {
        return akreditasi
    }

    fun getPROVINSI(): String? {
        return provinsi
    }

    fun getWORLDRANK(): String? {
        return worldrank
    }

    fun getJUMLAHPRODI(): String? {
        return jumlah_prodi
    }

    fun getLINKDROP(): String? {
        return link_drop
    }
}