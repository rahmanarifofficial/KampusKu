package com.rahmanarifofficial.mypik_pusatinformasikampus.db

data class BeasiswaDB(
    val id: Long?, val idBeasiswa: String?, val beasiswa: String?, val deadline: String?,
    val kategori: String?, val jenisPembiayaan: String?, val penyelenggara: String?,
    val linkBanner: String?
) {
    companion object {
        const val TABLE_BEASISWA = "TABLE_BEASISWA"
        const val ID = "ID_"
        const val ID_BEASISWA = "ID_BEASISWA"
        const val BEASISWA = "BEASISWA"
        const val DEADLINE = "DEADLINE"
        const val KATEGORI = "KATEGORI"
        const val JENIS_PEMBIAYAAN = "JENIS_PEMBIAYAAN"
        const val PENYELENGGARA = "PENYELENGGARA"
        const val LINK_BANNER = "LINK_BANNER"
    }
}