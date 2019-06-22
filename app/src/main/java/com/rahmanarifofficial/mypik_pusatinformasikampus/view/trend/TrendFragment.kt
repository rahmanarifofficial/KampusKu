package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanPopulerListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.KampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.TrendPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.detailkampus.DetailKampusActivity
import kotlinx.android.synthetic.main.fragment_trend.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class TrendFragment : Fragment(), TrendView {
    private lateinit var adapter: JurusanPopulerListAdapter

    private var jurusanList: MutableList<Jurusan> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        TrendPresenter.showPopulerJurusan(this)
        adapter = JurusanPopulerListAdapter(jurusanList) {
            activity?.startActivity<DetailJurusanActivity>("kode" to "${it.id}")
        }
        rv_list_jurusan_populer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_list_jurusan_populer.adapter = adapter
        swiperefresh_trend.onRefresh {
            TrendPresenter.showPopulerJurusan(this)
        }
    }

    override fun showLoading() {
        swiperefresh_trend.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_trend.isRefreshing = false
    }

    override fun showPopulerJurusan(data: List<Jurusan>) {
        if (!data.isNullOrEmpty()) {
            jurusanList.clear()
            jurusanList.addAll(data)
            adapter.notifyDataSetChanged()
        }

    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
    }

}
