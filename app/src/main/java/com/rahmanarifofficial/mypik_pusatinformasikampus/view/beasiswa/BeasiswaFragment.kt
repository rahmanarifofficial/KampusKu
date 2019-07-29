package com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import android.util.Log
import android.view.*
import android.view.View.GONE
import android.widget.Button
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.BeasiswaInActiveListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.BeasiswaListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.BeasiswaPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG.TAG
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import java.util.*

class BeasiswaFragment : androidx.fragment.app.Fragment(), BeasiswaView, SearchView.OnQueryTextListener {

    private lateinit var swiperefresh_beasiswa: androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    private lateinit var btn_refresh_beasiswa: Button
    private lateinit var rv_list_beasiswa: androidx.recyclerview.widget.RecyclerView
    private lateinit var rv_list_inactive_beasiswa: androidx.recyclerview.widget.RecyclerView
    private lateinit var adapterBeasiswaActive: BeasiswaListAdapter
    private lateinit var adapterBeasiswaInActive: BeasiswaInActiveListAdapter

    private var beasiswaList: MutableList<Beasiswa> = mutableListOf()
    private var inActiveBeasiswaList: MutableList<Beasiswa> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_beasiswa))
        val v = inflater.inflate(R.layout.fragment_beasiswa, container, false)
        swiperefresh_beasiswa = v.findViewById(R.id.swiperefresh_beasiswa)
        btn_refresh_beasiswa = v.findViewById(R.id.btn_refresh_beasiswa)
        rv_list_beasiswa = v.findViewById(R.id.rv_list_beasiswa)
        rv_list_inactive_beasiswa = v.findViewById(R.id.rv_list_inactive_beasiswa)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        BeasiswaPresenter.showBeasiswa(this)
        BeasiswaPresenter.showInActiveBeasiswa(this)
        adapterBeasiswaActive = BeasiswaListAdapter(beasiswaList) {
            activity?.startActivity<DetailBeasiwaActivity>("kode" to "${it.id}")
        }
        rv_list_beasiswa.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        rv_list_beasiswa.adapter = adapterBeasiswaActive
        adapterBeasiswaInActive = BeasiswaInActiveListAdapter(inActiveBeasiswaList) {
            activity?.startActivity<DetailBeasiwaActivity>("kode" to "${it.id}")
        }
        rv_list_inactive_beasiswa.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        rv_list_inactive_beasiswa.adapter = adapterBeasiswaInActive
        swiperefresh_beasiswa.onRefresh {
            BeasiswaPresenter.showBeasiswa(this)
            BeasiswaPresenter.showInActiveBeasiswa(this)
        }
        btn_refresh_beasiswa.visibility = GONE
        btn_refresh_beasiswa.setOnClickListener {
            btn_refresh_beasiswa.visibility = View.GONE
            BeasiswaPresenter.showBeasiswa(this)
            BeasiswaPresenter.showInActiveBeasiswa(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_beasiswa)
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun showLoading() {
        swiperefresh_beasiswa.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_beasiswa.isRefreshing = false
    }

    override fun showBeasiswa(data: List<Beasiswa>) {
        swiperefresh_beasiswa.isRefreshing = false
        beasiswaList.clear()
        beasiswaList.addAll(data)
        adapterBeasiswaActive.notifyDataSetChanged()
    }

    override fun showInActiveBeasiswa(data: List<Beasiswa>) {
//        swiperefresh_beasiswa.isRefreshing = false
        inActiveBeasiswaList.clear()
        inActiveBeasiswaList.addAll(data)
        Collections.reverse(inActiveBeasiswaList)
        adapterBeasiswaInActive.notifyDataSetChanged()
    }

    override fun showError(data: String) {
        Log.d(TAG, data)
        swiperefresh_beasiswa.isRefreshing = false
        btn_refresh_beasiswa.visibility = View.VISIBLE
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0!!.isNotEmpty()) {
            BeasiswaPresenter.searchBeasiswa(this, p0)
        } else {
            BeasiswaPresenter.showBeasiswa(this)
        }
        return false

    }

}
