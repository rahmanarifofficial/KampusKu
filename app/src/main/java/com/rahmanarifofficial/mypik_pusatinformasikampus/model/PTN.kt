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

    fun getKODE(): String? {
        return kode
    }

    fun setKODE(kODE: String) {
        this.kode = kODE
    }

    fun getNAMA(): String? {
        return nama
    }

    fun setNAMA(nAMA: String) {
        this.nama = nAMA
    }

    fun getLINKPMB(): String? {
        return link_pmb
    }

    fun setLINKPMB(lINKPMB: String) {
        this.link_pmb = lINKPMB
    }

    fun getLINKLOGO(): String? {
        return link_logo
    }

    fun setLINKLOGO(lINKLOGO: String) {
        this.link_logo = lINKLOGO
    }

    fun getAKREDITASI(): String? {
        return akreditasi
    }

    fun setAKREDITASI(aKREDITASI: String) {
        this.akreditasi = aKREDITASI
    }

    fun getPROVINSI(): String? {
        return provinsi
    }

    fun setPROVINSI(pROVINSI: String) {
        this.provinsi = pROVINSI
    }

    fun getWORLDRANK(): String? {
        return worldrank
    }

    fun setWORLDRANK(wORLDRANK: String) {
        this.worldrank = wORLDRANK
    }

    fun getJUMLAHPRODI(): String? {
        return jumlah_prodi
    }

    fun setJUMLAHPRODI(kuota: String) {
        this.jumlah_prodi = kuota
    }

    fun getLINKDROP(): String? {
        return link_drop
    }

    fun setLINKDROP(link_drop: String?) {
        this.link_drop = link_drop
    }
}