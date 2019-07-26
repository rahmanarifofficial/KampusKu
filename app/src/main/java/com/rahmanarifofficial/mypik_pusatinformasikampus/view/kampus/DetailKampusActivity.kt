package com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus

import android.content.Intent
import android.database.sqlite.SQLiteException
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.ProdiPagerAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.JurusanDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.KampusDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.database
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG.TAG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kampus.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DetailKampusActivity : AppCompatActivity(),
    DetailKampusView {

    private lateinit var ptn: PTN

    private var menuItem: Menu? = null
    private var isFavorite = false
    private lateinit var kode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kampus)

        setSupportActionBar(toolbar_detail_kampus);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        kode = intent.getStringExtra("kode")
        KampusPresenter.getPTN(this, kode)

        setupViewPager(view_pager_prodi)
        tab_prodi.setupWithViewPager(view_pager_prodi)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        favoriteState()
        setFavorite()
        return true
    }

    override fun showPtn(data: List<PTN>) {
        ptn = PTN(
            data[0].getKODE(), data[0].getNAMA(), data[0].getLINKPMB(), data[0].getLINKLOGO(), data[0].getAKREDITASI(),
            data[0].getPROVINSI(), data[0].getWORLDRANK(), data[0].getJUMLAHPRODI(), data[0].getLINKDROP()
        )
        collapsing_toolbar_detail_kampus.title = data.get(0).getNAMA()
        collapsing_toolbar_detail_kampus.setExpandedTitleColor(Color.parseColor("#44ffffff"))
        tv_akreditasi_detail_ptn.text = data.get(0).getAKREDITASI()
        tv_rank_detail_ptn.text = data.get(0).getWORLDRANK()
        tv_web_detail_ptn.text = data.get(0).getLINKPMB()
        tv_provinsi_detail_ptn.text = data.get(0).getPROVINSI()
        tv_jumlah_prodi_detail_ptn.text = data.get(0).getJUMLAHPRODI()
        if (!data.get(0).getLINKDROP().equals("")) {
            Picasso.get().load(data.get(0).getLINKDROP()).placeholder(R.drawable.ic_university_campus).centerCrop()
                .fit()
                .into(iv_foto_detail_ptn)
        }
        if (!data.get(0).getLINKPMB().isNullOrEmpty()) {
            btnWebsite.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.get(0).getLINKPMB()))
                startActivity(intent)
            }
        }
    }

    override fun showError(error: String) {
        Log.d(TAG, error)
        KampusPresenter.getPTN(this, kode)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

    private fun setupViewPager(pager: ViewPager) {
        val adapter = ProdiPagerAdapter(supportFragmentManager)

        val saintek = ProdiSaintekFragment.saintekIntance(kode)
        adapter.addFragment(saintek, "SAINTEK")

        val soshum = ProdiSoshumFragment.soshumIntance(kode)
        adapter.addFragment(soshum, "SOSHUM")

        pager.adapter = adapter
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
    }

    private fun favoriteState() {
        database.use {
            val result = select(KampusDB.TABLE_KAMPUS).whereArgs(
                "(KODE = {kode})",
                "kode" to kode
            )
            val favorite = result.parseList(classParser<KampusDB>())
            if (!favorite.isEmpty()) {
                isFavorite = true
            }

        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    KampusDB.TABLE_KAMPUS,
                    KampusDB.KODE to ptn.getKODE(),
                    KampusDB.NAMA to ptn.getNAMA(),
                    KampusDB.LINK_PMB to ptn.getLINKPMB(),
                    KampusDB.LINK_LOGO to ptn.getLINKLOGO(),
                    KampusDB.AKREDITASI to ptn.getAKREDITASI()
                )
            }
            toast(getString(R.string.tambah_favorite))
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(KampusDB.TABLE_KAMPUS, "(KODE = {kode})", "kode" to kode)
            }
            toast(getString(R.string.hapus_favorite))
        } catch (e: SQLiteException) {
            Log.d(TAG, e.message)
        }
    }

}
