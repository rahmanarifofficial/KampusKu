package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Prodi {
    @SerializedName("kode_ptn")
    @Expose
    private var kodePtn: String? = null
    @SerializedName("kode_prodi")
    @Expose
    private var kodeProdi: String? = null
    @SerializedName("nama_prodi")
    @Expose
    private var namaProdi: String? = null
    @SerializedName("daya_tampung_2019")
    @Expose
    private var dayaTampung2019: String? = null
    @SerializedName("peminat_2018")
    @Expose
    private var peminat2018: String? = null
    @SerializedName("jenis_portofolio")
    @Expose
    private var jenisPortofolio: String? = null
    @SerializedName("tipe")
    @Expose
    private var tipe: String? = null
    @SerializedName("nama_universitas")
    @Expose
    private var namaUniversitas: String? = null
    @SerializedName("akreditasi")
    @Expose
    private var akreditasi: String? = null
    @SerializedName("link_pmb")
    @Expose
    private var link_pmb: String? = null
    @SerializedName("link_logo")
    @Expose
    private var link_logo: String? = null

    fun getKodePtn(): String? {
        return kodePtn
    }

    fun setKodePtn(kodePtn: String) {
        this.kodePtn = kodePtn
    }

    fun getKodeProdi(): String? {
        return kodeProdi
    }

    fun setKodeProdi(kodeProdi: String) {
        this.kodeProdi = kodeProdi
    }

    fun getNamaProdi(): String? {
        return namaProdi
    }

    fun setNamaProdi(namaProdi: String) {
        this.namaProdi = namaProdi
    }

    fun getDayaTampung2019(): String? {
        return dayaTampung2019
    }

    fun setDayaTampung2019(dayaTampung2019: String) {
        this.dayaTampung2019 = dayaTampung2019
    }

    fun getPeminat2018(): String? {
        return peminat2018
    }

    fun setPeminat2018(peminat2018: String) {
        this.peminat2018 = peminat2018
    }

    fun getJenisPortofolio(): String? {
        return jenisPortofolio
    }

    fun setJenisPortofolio(jenisPortofolio: String) {
        this.jenisPortofolio = jenisPortofolio
    }

    fun getTipe(): String? {
        return tipe
    }

    fun setTipe(tipe: String) {
        this.tipe = tipe
    }

    fun getNamaUniversitas(): String? {
        return namaUniversitas
    }

    fun setNamaUniversitas(namaUniversitas: String) {
        this.namaUniversitas = namaUniversitas
    }

    fun getAkreditasi(): String? {
        return akreditasi
    }

    fun setAkreditasi(akreditasi: String) {
        this.akreditasi = akreditasi
    }

    fun getLinkPMB(): String? {
        return link_pmb
    }

    fun setLINKPMB(lINKPMB: String) {
        this.link_pmb = lINKPMB
    }

    fun getLinkLogo(): String? {
        return link_logo
    }

    fun setLINKLOGO(lINKLOGO: String) {
        this.link_logo = lINKLOGO
    }

}