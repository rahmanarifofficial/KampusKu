package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.AkunPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.LoginPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG.TAG
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.favorites.FavoritesActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.support.v4.startActivity

class ProfileFragment : androidx.fragment.app.Fragment(), ProfileView, View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var loginPrefs: LoginPreferences
    private lateinit var prefs: AuthPreferences
    private lateinit var nama: String
    private lateinit var email: String
    private lateinit var alamat: String
    private lateinit var asalSekolah: String
    private lateinit var facebook: String
    private lateinit var instagram: String
    private lateinit var noTelp: String
    private lateinit var foto: String

    private lateinit var iv_info_foto_profil_akun: ImageView
    private lateinit var tv_info_nama_akun: TextView
    private lateinit var tv_info_email_akun: TextView
    private lateinit var info_profile: ConstraintLayout
    private lateinit var favorite_profile: LinearLayout
    private lateinit var about_profile: LinearLayout
    private lateinit var help_profile: LinearLayout
    private lateinit var logout_profile: LinearLayout
    private lateinit var btn_refresh_profile: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_profil))
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        iv_info_foto_profil_akun = v.findViewById(R.id.iv_info_foto_profil_akun)
        tv_info_nama_akun = v.findViewById(R.id.tv_info_nama_akun)
        tv_info_email_akun = v.findViewById(R.id.tv_info_email_akun)
        info_profile = v.findViewById(R.id.info_profile)
        favorite_profile = v.findViewById(R.id.favorite_profile)
        about_profile = v.findViewById(R.id.about_profile)
        help_profile = v.findViewById(R.id.help_profile)
        logout_profile = v.findViewById(R.id.logout_profile)
        btn_refresh_profile = v.findViewById(R.id.btn_refresh_profile)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        prefs = AuthPreferences(activity!!)
        loginPrefs = LoginPreferences(activity!!)
        val email = prefs.getEmail()
        val password = prefs.getPassword()

        auth = FirebaseAuth.getInstance()

        AkunPresenter.getPengguna(this, email, password)
        info_profile.setOnClickListener(this)
        favorite_profile.setOnClickListener(this)
        about_profile.setOnClickListener(this)
        help_profile.setOnClickListener(this)
        logout_profile.setOnClickListener(this)

        btn_refresh_profile.setOnClickListener {
            btn_refresh_profile.visibility = View.GONE
            AkunPresenter.getPengguna(this, email, password)
        }

    }

    override fun showProfil(data: List<Pengguna>) {
        if (!data.isNullOrEmpty()) {
            prefs.setIdPengguna(data[0].idPengguna!!)
            nama = data[0].namaPengguna!!
            email = data[0].emailPengguna!!
            alamat = data[0].alamatPengguna!!
            asalSekolah = data[0].asal_sekolah!!
            facebook = data[0].facebook!!
            instagram = data[0].instagram!!
            noTelp = data[0].no_telp!!
            if (!data[0].linkFoto.isNullOrEmpty()) {
                foto = data[0].linkFoto!!
            } else
                foto = ""

            tv_info_nama_akun.text = nama
            tv_info_email_akun.text = email
            if (!data[0].linkFoto.isNullOrEmpty()) {
                Picasso.get().load(foto).placeholder(R.drawable.ic_user_avatar).centerCrop().fit()
                    .into(iv_info_foto_profil_akun)
            }
        }
    }


    override fun showToast(data: String) {
        Log.d(TAG, data)
        btn_refresh_profile.visibility = View.VISIBLE
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
            R.id.favorite_profile -> {
                startActivity<FavoritesActivity>()
            }
            R.id.help_profile -> {
                startActivity<BantuanActivity>()
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
