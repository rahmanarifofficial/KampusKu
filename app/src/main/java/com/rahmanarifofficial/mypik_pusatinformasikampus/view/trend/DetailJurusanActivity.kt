package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import android.database.sqlite.SQLiteException
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanKampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.JurusanDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.database
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.TrendPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG.TAG
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.DetailKampusActivity
import kotlinx.android.synthetic.main.activity_detail_jurusan.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class DetailJurusanActivity : AppCompatActivity(), DetailJurusanView {

    private lateinit var jurusan: Jurusan

    private var menuItem: Menu? = null
    private var isFavorite = false
    private var idJurusan = ""

    private lateinit var adapter: JurusanKampusListAdapter
    private var prodiList: MutableList<Prodi> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jurusan)
        idJurusan = intent.getStringExtra("kode")
        TrendPresenter.showDetailJurusan(idJurusan, this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = JurusanKampusListAdapter(prodiList) {
            startActivity<DetailKampusActivity>("kode" to "${it.getKodePtn()}")
        }
        rv_list_ptn_jurusan.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rv_list_ptn_jurusan.adapter = adapter
        swiperefresh_detail_jurusan.onRefresh {
            TrendPresenter.showDetailJurusan(idJurusan, this)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        favoriteState()
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }
                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        swiperefresh_detail_jurusan.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_detail_jurusan.isRefreshing = false
    }

    override fun showJurusan(data: List<Jurusan>) {
        if (!data.isNullOrEmpty()) {
            jurusan = Jurusan(data[0].id, data[0].jurusan, data[0].namaKelompok, data[0].mapel, data[0].tipe)
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
        Log.d(TAG, data)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
    }

    private fun favoriteState() {
        database.use {
            val result = select(JurusanDB.TABLE_JURUSAN).whereArgs(
                "(ID_JURUSAN = {idJurusan})",
                "idJurusan" to idJurusan
            )
            val favorite = result.parseList(classParser<JurusanDB>())
            if (!favorite.isEmpty()) {
                isFavorite = true
            }

        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    JurusanDB.TABLE_JURUSAN,
                    JurusanDB.ID_JURUSAN to jurusan.id,
                    JurusanDB.JURUSAN to jurusan.jurusan,
                    JurusanDB.NAMA_KELOMPOK to jurusan.namaKelompok,
                    JurusanDB.MAPEL to jurusan.mapel,
                    JurusanDB.TIPE to jurusan.tipe
                )
            }
            Log.d(TAG, jurusan.namaKelompok)
            toast(getString(R.string.tambah_favorite))
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(JurusanDB.TABLE_JURUSAN, "(ID_JURUSAN = {idJurusan})", "idJurusan" to idJurusan)
            }
            toast(getString(R.string.hapus_favorite))
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }
    }

}
