package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_profil.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profil)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_profile)

        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")
        val alamat = intent.getStringExtra("alamat")
        val asalSekolah = intent.getStringExtra("asal")
        val facebook = "\t"+intent.getStringExtra("facebook")
        val instagram = "\t"+intent.getStringExtra("instagram")
        val noTelp = intent.getStringExtra("noTelp")
        val foto = intent.getStringExtra("foto")

        tv_nama_akun.text = nama
        tv_email_akun.text = email
        tv_alamat_akun.text = alamat
        tv_asal_sekolah_akun.text = asalSekolah
        tv_facebook_akun.text = facebook
        tv_intagram_akun.text = instagram
        tv_no_telp_akun.text = noTelp
        if (!foto.isNullOrEmpty()) {
            Picasso.get().load(foto).placeholder(R.drawable.ic_user_avatar).centerCrop().fit()
                .into(iv_foto_profil_akun)
        }
        btn_edit_profile.setOnClickListener {
            startActivity<EditProfileActivity>()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
