package com.rahmanarifofficial.mypik_pusatinformasikampus.presenter

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiBuilder
import com.rahmanarifofficial.mypik_pusatinformasikampus.network.ApiService
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.DateTimeNow
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.LoginPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun.AkunView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun.ProfileView
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun.NewProfileView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AkunPresenter {
    companion object {
        fun loginPengguna(view: AkunView, email: String, password: String, context: Context) {
            view.showProgress()
            val prefs = LoginPreferences(context)
            val mAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view.hideProgress()
                    prefs.setWasLogin(true)
                    view.updateUI(mAuth.currentUser!!, 1)
                } else {
                    view.hideProgress()
                    prefs.setWasLogin(false)
                    view.showToast("Gagal Untuk Masuk. \nCek Email dan Password anda")
                }
            }
        }

        fun daftarPengguna(view: AkunView, email: String, password: String, context: Context) {
            val prefs = LoginPreferences(context)
            val mAuth = FirebaseAuth.getInstance()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    prefs.setWasLogin(true)
                    view.updateUI(task.result!!.user, 2)
                } else {
                    prefs.setWasLogin(false)
                    view.showToast("Akun Telah Terdaftar, Cobalah menggunakan Email Lain")
                }
            }
        }

        fun insertPengguna(
            view: NewProfileView, email: String, password: String, nama: String, noTelp: String,
            alamat: String, sekolah: String, instagram: String?, facebook: String?, foto: Uri
        ) {
            view.showProgress()
            val storageRef = FirebaseStorage.getInstance()
                .getReference("pengguna/" + email + DateTimeNow.DATENOW() + DateTimeNow.TIMENOW());
            storageRef.putFile(foto)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener {
                        val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
                        val call = apiService?.insertPengguna(
                            nama,
                            email,
                            password,
                            alamat,
                            noTelp,
                            sekolah,
                            instagram,
                            facebook,
                            it.toString()
                        )
                        call?.enqueue(object : Callback<Pengguna> {
                            override fun onFailure(call: Call<Pengguna>, t: Throwable) {
                                view.hideProgress()
                            }

                            override fun onResponse(call: Call<Pengguna>, response: Response<Pengguna>) {
                                view.hideProgress()
                                view.updateUI()
                            }
                        })
                    }
                }
                .addOnFailureListener {
                    val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
                    val call = apiService?.insertPengguna(
                        nama,
                        email,
                        password,
                        alamat,
                        noTelp,
                        sekolah,
                        instagram,
                        facebook,
                        null
                    )
                    call?.enqueue(object : Callback<Pengguna> {
                        override fun onFailure(call: Call<Pengguna>, t: Throwable) {
                            view.hideProgress()
                        }

                        override fun onResponse(call: Call<Pengguna>, response: Response<Pengguna>) {
                            view.hideProgress()
                            view.updateUI()
                        }
                    })
                }
        }

        fun getPengguna(view: ProfileView, email: String, password: String) {
            val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
            val call = apiService?.getPengguna("'" + email + "'", "'" + password + "'")
            call?.enqueue(object : Callback<List<Pengguna>> {
                override fun onFailure(call: Call<List<Pengguna>>, t: Throwable) {
                    view.showToast(t.message!!)
                }

                override fun onResponse(call: Call<List<Pengguna>>, response: Response<List<Pengguna>>) {
                    if (!response.body()!!.isEmpty()) {
                        view.showProfil(response.body()!!)
                    } else {
                        view.updateUI()
                    }
                }
            })
        }
    }
}