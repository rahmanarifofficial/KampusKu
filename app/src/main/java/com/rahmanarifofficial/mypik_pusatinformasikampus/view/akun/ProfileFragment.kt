package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Pengguna
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.AkunPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileView {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val prefs = AuthPreferences(activity!!)
        val email = prefs.getEmail()
        val password = prefs.getPassword()

        auth = FirebaseAuth.getInstance()

        AkunPresenter.getPengguna(this, email, password)
        btnBantuan.setOnClickListener {

        }
        btnLogout.setOnClickListener {
            auth.signOut()
            val fm = fragmentManager
            val ft = fm!!.beginTransaction()
            ft.replace(R.id.main_container, AuthentikasiFragment())
            ft.commit()
        }
    }

    override fun showProfil(data: List<Pengguna>) {
        text_alamat.visibility = View.VISIBLE
        text_asal_sekolah.visibility = View.VISIBLE
        text_telepon.visibility = View.VISIBLE
        gambar_facebook.visibility = View.VISIBLE
        gambar_instagram.visibility = View.VISIBLE
        btnBantuan.visibility = View.VISIBLE
        btnLogout.visibility = View.VISIBLE
        tv_nama_akun.text = data[0].namaPengguna
        tv_alamat_akun.text = data[0].alamatPengguna
        tv_asal_sekolah_akun.text = data[0].asal_sekolah
        tv_email_akun.text = data[0].emailPengguna
        tv_facebook_akun.text = data[0].facebook
        tv_intagram_akun.text = data[0].instagram
        tv_no_telp_akun.text = data[0].no_telp
        Picasso.get().load(data[0].linkFoto).placeholder(R.drawable.ic_user_avatar).centerCrop().fit()
            .into(iv_foto_profil_akun)
    }

    override fun showToast(data: String) {
        Log.d("TAGERROR",data)
    }

    override fun updateUI() {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        ft.replace(R.id.main_container,
            NewProfileFragment()
        )
        ft.commit()
    }

}
