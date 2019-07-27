package com.rahmanarifofficial.mypik_pusatinformasikampus.db

data class JurusanDB(
    val id: Long?, val idJurusan: String?, val jurusan: String?, val namaKelompok: String?,
    val mapel: String?, val tipe: String?
) {
    companion object {
        const val TABLE_JURUSAN = "TABLE_JURUSAN"
        const val ID = "ID_"
        const val ID_JURUSAN = "ID_JURUSAN"
        const val JURUSAN = "JURUSAN"
        const val NAMA_KELOMPOK = "NAMA_KELOMPOK"
        const val MAPEL = "MAPEL"
        const val TIPE = "TIPE"
    }
}