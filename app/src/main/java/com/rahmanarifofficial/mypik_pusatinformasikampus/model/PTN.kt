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
    private var linkpmb: String? = null
    @SerializedName("LINK_LOGO")
    @Expose
    private var linklogo: String? = null

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
        return linkpmb
    }

    fun setLINKPMB(lINKPMB: String) {
        this.linkpmb = lINKPMB
    }

    fun getLINKLOGO(): String? {
        return linklogo
    }

    fun setLINKLOGO(lINKLOGO: String) {
        this.linklogo = lINKLOGO
    }
}