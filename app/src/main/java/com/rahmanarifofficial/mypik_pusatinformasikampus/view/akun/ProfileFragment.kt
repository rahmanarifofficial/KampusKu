package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.AkunPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.LoginPreferences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.startActivity


class ProfileFragment : Fragment(), ProfileView, View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var loginPrefs: LoginPreferences
    private lateinit var nama: String
    private lateinit var email: String
    private lateinit var alamat: String
    private lateinit var asalSekolah: String
    private lateinit var facebook: String
    private lateinit var instagram: String
    private lateinit var noTelp: String
    private lateinit var foto: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_profil))
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val prefs = AuthPreferences(activity!!)
        loginPrefs = LoginPreferences(activity!!)
        val email = prefs.getEmail()
        val password = prefs.getPassword()

        auth = FirebaseAuth.getInstance()

        AkunPresenter.getPengguna(this, email, password)
        info_profile.setOnClickListener(this)
        about_profile.setOnClickListener(this)
        help_profile.setOnClickListener(this)
        logout_profile.setOnClickListener(this)
    }

    override fun showProfil(data: List<Pengguna>) {
        nama = data[0].namaPengguna!!
        email = data[0].emailPengguna!!
        alamat = data[0].alamatPengguna!!
        asalSekolah = data[0].asal_sekolah!!
        facebook = data[0].facebook!!
        instagram = data[0].instagram!!
        noTelp = data[0].no_telp!!
        foto = data[0].linkFoto!!

        tv_info_nama_akun.text = nama
        tv_info_email_akun.text = email
        if (!data[0].linkFoto.isNullOrEmpty()) {
            Picasso.get().load(foto).placeholder(R.drawable.ic_user_avatar).centerCrop().fit()
                .into(iv_info_foto_profil_akun)
        }
    }


    override fun showToast(data: String) {
        Log.d("TAGERROR", data)
    }

    override fun updateUI() {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        ft.replace(R.id.main_container, NewProfileFragment())
        ft.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.info_profile -> {
                startActivity<DetailProfilActivity>(
                    "nama" to nama,
                    "email" to email,
                    "alamat" to alamat,
                    "asal" to asalSekolah,
                    "facebook" to facebook,
                    "instagram" to instagram,
                    "noTelp" to noTelp,
                    "foto" to foto
                )
            }
            R.id.help_profile -> {
            }
            R.id.about_profile -> {
                startActivity<AboutActivity>()
            }
            R.id.logout_profile -> {
                auth.signOut()
                loginPrefs.setWasLogin(false)
                val fm = fragmentManager
                val ft = fm!!.beginTransaction()
                ft.replace(R.id.main_container, AuthentikasiFragment())
                ft.commit()
            }
        }
    }

}
