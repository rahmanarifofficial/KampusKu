package com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.BeasiswaListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.BeasiswaPresenter
import kotlinx.android.synthetic.main.fragment_beasiswa.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class BeasiswaFragment : Fragment(), BeasiswaView {

    private lateinit var adapter: BeasiswaListAdapter

    private var beasiswaList: MutableList<Beasiswa> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_beasiswa))
        return inflater.inflate(R.layout.fragment_beasiswa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        BeasiswaPresenter.showBeasiswa(this)
        adapter = BeasiswaListAdapter(beasiswaList) {
            activity?.startActivity<DetailBeasiwaActivity>("kode" to "${it.id}")
        }
        rv_list_beasiswa.layoutManager = LinearLayoutManager(activity)
        rv_list_beasiswa.adapter = adapter
        swiperefresh_beasiswa.onRefresh {
            BeasiswaPresenter.showBeasiswa(this)
        }
    }

    override fun showLoading() {
        swiperefresh_beasiswa.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_beasiswa.isRefreshing = false
    }

    override fun showBeasiswa(data: List<Beasiswa>) {
        beasiswaList.clear()
        beasiswaList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
    }

}
