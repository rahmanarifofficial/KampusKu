package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanPopulerListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.TrendPresenter
import kotlinx.android.synthetic.main.fragment_trend.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class TrendFragment : Fragment(), TrendView, AdapterView.OnItemSelectedListener {

    private lateinit var adapter: JurusanPopulerListAdapter
    private lateinit var adapterJurusan: JurusanListAdapter

    private var jurusanPopulerList: MutableList<Jurusan> = mutableListOf()
    private var jurusanList: MutableList<Jurusan> = mutableListOf()
    private val kelompok = arrayOf(
        "Budaya & Bahasa",
        "Ekonomi & Bisnis",
        "Kesehatan",
        "Pendidikan",
        "Pertanian",
        "Science",
        "Seni",
        "Sosial Humaniora",
        "Teknik",
        "Komputer & Teknologi",
        "Profesi & Ilmu Terapan"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        TrendPresenter.showPopulerJurusan(this)
        adapter = JurusanPopulerListAdapter(jurusanPopulerList) {
            activity?.startActivity<DetailJurusanActivity>("kode" to "${it.id}")
        }
        rv_list_jurusan_populer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_list_jurusan_populer.adapter = adapter
        swiperefresh_trend.onRefresh {
            TrendPresenter.showPopulerJurusan(this)
        }
        spinadapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_dropdown_item, kelompok)
        spinner_kelompok_jurusan.adapter = spinadapter
        spinner_kelompok_jurusan.setOnItemSelectedListener(this)
        adapterJurusan = JurusanListAdapter(jurusanList) {
            activity?.startActivity<DetailJurusanActivity>("kode" to "${it.id}")
        }
        rv_list_jurusan.layoutManager = LinearLayoutManager(activity)
        rv_list_jurusan.adapter = adapterJurusan

    }

    override fun showLoading() {
        swiperefresh_trend.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_trend.isRefreshing = false
    }

    override fun showPopulerJurusan(data: List<Jurusan>) {
        if (!data.isNullOrEmpty()) {
            jurusanPopulerList.clear()
            jurusanPopulerList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    private lateinit var spinadapter: ArrayAdapter<String>

    override fun showJurusanList(data: List<Jurusan>) {
        if (!data.isNullOrEmpty()) {
            Log.d("TAGTEST", data[0].jurusan)
            jurusanList.clear()
            jurusanList.addAll(data)
            adapterJurusan.notifyDataSetChanged()
        }

    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TrendPresenter.getListJurusan(position + 1, this);
    }

}
