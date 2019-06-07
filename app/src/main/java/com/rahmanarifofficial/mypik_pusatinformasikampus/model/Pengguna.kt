package com.rahmanarifofficial.mypik_pusatinformasikampus.model

import com.google.gson.annotations.SerializedName

data class Pengguna(
    @SerializedName("id_pengguna")
    var idPengguna: String? = null,

    @SerializedName("nama_pengguna")
    var namaPengguna: String? = null,

    @SerializedName("email_pengguna")
    var emailPengguna: String? = null,

    @SerializedName("password_pengguna")
    var passwordPengguna: String? = null,

    @SerializedName("alamat_pengguna")
    var alamatPengguna: String? = null,

    @SerializedName("no_telp")
    var no_telp: String? = null,

    @SerializedName("asal_sekolah")
    var asal_sekolah: String? = null,

    @SerializedName("instagram")
    var instagram: String? = null,

    @SerializedName("facebook")
    var facebook: String? = null,

    @SerializedName("link_foto")
    var linkFoto: String? = null

)