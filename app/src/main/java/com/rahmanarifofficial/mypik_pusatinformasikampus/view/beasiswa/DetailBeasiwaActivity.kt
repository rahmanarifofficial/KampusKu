package com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.BeasiswaPresenter
import kotlinx.android.synthetic.main.activity_detail_beasiwa.*
import org.jetbrains.anko.support.v4.onRefresh

class DetailBeasiwaActivity : AppCompatActivity(), BeasiswaView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_beasiwa)
        val id = intent.getStringExtra("kode")
        BeasiswaPresenter.showDetailBeasiswa(this, id)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        swiperefresh_detail_beasiswa.onRefresh {
            BeasiswaPresenter.showDetailBeasiswa(this, id)
        }
    }

    override fun showLoading() {
        swiperefresh_detail_beasiswa.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_detail_beasiswa.isRefreshing = false
    }

    override fun showBeasiswa(data: List<Beasiswa>) {
        if (!data.isNullOrEmpty()) {
            supportActionBar?.title = data[0].beasiswa
            tv_deadline_detail_beasiswa.text = data[0].deadline
            tv_jenis_detail_beasiswa.text = data[0].jenisPembiayaan
            tv_kategori_detail_beasiswa.text = data[0].kategori
            tv_berkas_detail_beasiswa.text = data[0].berkasPendaftaran
            tv_deskripsi_detail_beasiswa.text = data[0].deskripsi
            tv_komponen_detail_beasiswa.text = data[0].komponenBeasiswa
            tv_proses_detail_beasiswa.text = data[0].prosesPendaftaran
            tv_syarat_detail_beasiswa.text = data[0].persyaratanPendaftar
            tv_nama_penyelenggara_detail_beasiswa.text = data[0].penyelenggara
        }
    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
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
