package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PTN {
    @SerializedName("KODE")
    @Expose
    private var kODE: String? = null
    @SerializedName("NAMA")
    @Expose
    private var nAMA: String? = null
    @SerializedName("LINK_PMB")
    @Expose
    private var lINKPMB: String? = null
    @SerializedName("LINK_LOGO")
    @Expose
    private var lINKLOGO: String? = null
    @SerializedName("AKREDITASI")
    @Expose
    private var aKREDITASI: String? = null
    @SerializedName("PROVINSI")
    @Expose
    private var pROVINSI: String? = null
    @SerializedName("WORLD_RANK")
    @Expose
    private var wORLDRANK: String? = null

    fun getKODE(): String? {
        return kODE
    }

    fun setKODE(kODE: String) {
        this.kODE = kODE
    }

    fun getNAMA(): String? {
        return nAMA
    }

    fun setNAMA(nAMA: String) {
        this.nAMA = nAMA
    }

    fun getLINKPMB(): String? {
        return lINKPMB
    }

    fun setLINKPMB(lINKPMB: String) {
        this.lINKPMB = lINKPMB
    }

    fun getLINKLOGO(): String? {
        return lINKLOGO
    }

    fun setLINKLOGO(lINKLOGO: String) {
        this.lINKLOGO = lINKLOGO
    }

    fun getAKREDITASI(): String? {
        return aKREDITASI
    }

    fun setAKREDITASI(aKREDITASI: String) {
        this.aKREDITASI = aKREDITASI
    }

    fun getPROVINSI(): String? {
        return pROVINSI
    }

    fun setPROVINSI(pROVINSI: String) {
        this.pROVINSI = pROVINSI
    }

    fun getWORLDRANK(): String? {
        return wORLDRANK
    }

    fun setWORLDRANK(wORLDRANK: String) {
        this.wORLDRANK = wORLDRANK
    }
}