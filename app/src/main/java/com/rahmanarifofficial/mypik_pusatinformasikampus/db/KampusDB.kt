package com.rahmanarifofficial.mypik_pusatinformasikampus.db

data class KampusDB(
    val id: Long?, val kode: String?, val nama: String?, val linkPMB: String?, val linkLogo: String,
    val akreditasi: String?
) {
    companion object {
        const val TABLE_KAMPUS = "TABLE_KAMPUS"
        const val ID = "ID_"
        const val KODE = "KODE"
        const val NAMA = "NAMA"
        const val LINK_PMB = "LINK_PMB"
        const val LINK_LOGO = "LINK_LOGO"
        const val AKREDITASI = "AKREDITASI"
    }
}