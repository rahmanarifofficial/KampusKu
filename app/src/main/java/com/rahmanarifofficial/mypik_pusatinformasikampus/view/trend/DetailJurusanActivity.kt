package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanKampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.TrendPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.DetailKampusActivity
import kotlinx.android.synthetic.main.activity_detail_jurusan.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class DetailJurusanActivity : AppCompatActivity(), DetailJurusanView {

    private lateinit var adapter: JurusanKampusListAdapter
    private var prodiList: MutableList<Prodi> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jurusan)
        val id = intent.getStringExtra("kode")
        TrendPresenter.showDetailJurusan(id, this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = JurusanKampusListAdapter(prodiList) {
            startActivity<DetailKampusActivity>("kode" to "${it.getKodePtn()}")
        }
        rv_list_ptn_jurusan.layoutManager = LinearLayoutManager(this)
        rv_list_ptn_jurusan.adapter = adapter
        swiperefresh_detail_jurusan.onRefresh {
            TrendPresenter.showDetailJurusan(id, this)
        }

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
            TrendPresenter.showJurusanPTN(data[0].jurusan!!, this)
            supportActionBar?.title = data[0].jurusan
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

    override fun showUniv(data: List<Prodi>) {
        prodiList.clear()
        prodiList.addAll(data)
        adapter.notifyDataSetChanged()

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
