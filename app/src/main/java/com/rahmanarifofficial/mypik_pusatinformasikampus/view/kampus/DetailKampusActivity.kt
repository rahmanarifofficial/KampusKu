package com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.ProdiPagerAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kampus.*
import org.jetbrains.anko.support.v4.onRefresh


class DetailKampusActivity : AppCompatActivity(),
    DetailKampusView {

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
        swiperefresh_detail_ptn.onRefresh {
            KampusPresenter.getPTN(this, kode)
        }
    }

    override fun showPtn(data: List<PTN>) {
        swiperefresh_detail_ptn.isRefreshing = false
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
        Log.d("TAGERROR", error)
        KampusPresenter.getPTN(this, kode)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }

    private fun setupViewPager(pager: ViewPager) {
        val adapter = ProdiPagerAdapter(supportFragmentManager)

        val saintek =
            ProdiSaintekFragment.saintekIntance(
                kode
            )
        adapter.addFragment(saintek, "SAINTEK")

        val soshum =
            ProdiSoshumFragment.soshumIntance(
                kode
            )
        adapter.addFragment(soshum, "SOSHUM")

        pager.adapter = adapter
    }

}