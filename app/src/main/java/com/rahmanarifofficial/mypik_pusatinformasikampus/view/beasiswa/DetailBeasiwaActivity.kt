package com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa

import android.database.sqlite.SQLiteException
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.BeasiswaDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.database
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.BeasiswaPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG.TAG
import kotlinx.android.synthetic.main.activity_detail_beasiwa.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class DetailBeasiwaActivity : AppCompatActivity(), DetailBeasiswaView {

    private lateinit var beasiswa: Beasiswa

    private var menuItem: Menu? = null
    private var isFavorite = false
    private var idBeasiswa = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_beasiwa)
        idBeasiswa = intent.getStringExtra("kode")
        BeasiswaPresenter.showDetailBeasiswa(this, idBeasiswa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        swiperefresh_detail_beasiswa.onRefresh {
            BeasiswaPresenter.showDetailBeasiswa(this, idBeasiswa)
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
        swiperefresh_detail_beasiswa.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_detail_beasiswa.isRefreshing = false
    }

    override fun showBeasiswa(data: List<Beasiswa>?) {
        if (!data.isNullOrEmpty()) {
            beasiswa = Beasiswa(
                data[0].id,
                data[0].beasiswa,
                data[0].deadline,
                data[0].kategori,
                data[0].jenisPembiayaan,
                data[0].penyelenggara,
                data[0].linkBanner
            )
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

    override fun showError(data: String?) {
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
            val result = select(BeasiswaDB.TABLE_BEASISWA).whereArgs(
                "(ID_BEASISWA = {idBeasiswa})",
                "idBeasiswa" to idBeasiswa
            )
            val favorite = result.parseList(classParser<BeasiswaDB>())
            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(BeasiswaDB.TABLE_BEASISWA, "(ID_BEASISWA = {idBeasiswa})", "idBeasiswa" to idBeasiswa)
            }
            toast(getString(R.string.hapus_favorite))
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    BeasiswaDB.TABLE_BEASISWA,
                    BeasiswaDB.ID_BEASISWA to beasiswa.id,
                    BeasiswaDB.BEASISWA to beasiswa.beasiswa,
                    BeasiswaDB.DEADLINE to beasiswa.deadline,
                    BeasiswaDB.KATEGORI to beasiswa.kategori,
                    BeasiswaDB.JENIS_PEMBIAYAAN to beasiswa.jenisPembiayaan,
                    BeasiswaDB.PENYELENGGARA to beasiswa.penyelenggara,
                    BeasiswaDB.LINK_BANNER to beasiswa.linkBanner
                )
            }
            toast(getString(R.string.tambah_favorite))
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }
    }
}
