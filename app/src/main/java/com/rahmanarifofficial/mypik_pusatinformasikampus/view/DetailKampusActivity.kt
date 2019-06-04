package com.rahmanarifofficial.mypik_pusatinformasikampus.view

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.ProdiPagerAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter
import kotlinx.android.synthetic.main.activity_detail_kampus.*
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.toast


class DetailKampusActivity : AppCompatActivity(), DetailKampusView {

    private lateinit var kode: String
    private lateinit var tvAkreditas: TextView

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

    override fun showPtn(data: List<PTN>) {
        collapsing_toolbar_detail_kampus.title = data.get(0).getNAMA()
        collapsing_toolbar_detail_kampus.setExpandedTitleColor(Color.parseColor("#FFFFFF"))
        tv_akreditasi_detail_ptn.text = data.get(0).getAKREDITASI()
        tv_rank_detail_ptn.text = data.get(0).getWORLDRANK()
        tv_web_detail_ptn.text = data.get(0).getLINKPMB()
        tv_provinsi_detail_ptn.text = data.get(0).getPROVINSI()
    }

    override fun showError(error: String) {
        toast(error)
    }

    private fun setupViewPager(pager: ViewPager) {
        val adapter = ProdiPagerAdapter(supportFragmentManager)

        val saintek = ProdiSaintekFragment.saintekIntance(kode)
        adapter.addFragment(saintek, "SAINTEK")

        val soshum = ProdiSoshumFragment.soshumIntance(kode)
        adapter.addFragment(soshum, "SOSHUM")

        pager.adapter = adapter
    }

}
