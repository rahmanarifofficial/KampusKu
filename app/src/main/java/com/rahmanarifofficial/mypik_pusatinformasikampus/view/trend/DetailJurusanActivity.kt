package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.TrendPresenter
import kotlinx.android.synthetic.main.activity_detail_jurusan.*

class DetailJurusanActivity : AppCompatActivity(), DetailJurusanView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jurusan)
        val id = intent.getStringExtra("kode")
        TrendPresenter.showDetailJurusan(id, this)
    }

    override fun showLoading() {
        swiperefresh_detail_jurusan.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_detail_jurusan.isRefreshing = false
    }

    override fun showJurusan(data: List<Jurusan>) {
        if (!data.isNullOrEmpty()) {
            swiperefresh_detail_jurusan.isRefreshing = false
            tv_kelompok_detail_jurusan.text = data[0].namaKelompok
            tv_tipe_detail_jurusan.text = data[0].tipe
            tv_mapel_detail_jurusan.text = data[0].mapel
            tv_deskripsi_detail_jurusan.text = data[0].deskripsi
            tv_alasan_detail_jurusan.text = data[0].alasanPilih
            tv_karakter_detail_jurusan.text = data[0].karakterSiswa
            tv_prospek_kerja_detail_jurusan.text = data[0].prospekKerja
            tv_profesi_kerja_detail_jurusan.text = data[0].profesiKerja
            if (data[0].deskripsi.isNullOrEmpty()) {
                tv_deskripsi_detail_jurusan.text = getString(R.string.text_empty)
            }
            if (data[0].alasanPilih.isNullOrEmpty()) {
                tv_alasan_detail_jurusan.text = getString(R.string.text_empty)
            }
            if (data[0].karakterSiswa.isNullOrEmpty()) {
                tv_karakter_detail_jurusan.text = getString(R.string.text_empty)
            }
            if (data[0].prospekKerja.isNullOrEmpty()) {
                tv_prospek_kerja_detail_jurusan.text = getString(R.string.text_empty)
            }
            if (data[0].profesiKerja.isNullOrEmpty()) {
                tv_profesi_kerja_detail_jurusan.text = getString(R.string.text_empty)
            }
        }
    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
    }


}
