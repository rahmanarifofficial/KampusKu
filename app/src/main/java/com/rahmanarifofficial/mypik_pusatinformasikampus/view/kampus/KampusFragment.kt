package com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.KampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.AkunPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter
import kotlinx.android.synthetic.main.fragment_kampus.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class KampusFragment : Fragment(), KampusView, SearchView.OnQueryTextListener {

    private lateinit var swiperefresh_ptn: SwipeRefreshLayout
    private lateinit var btn_refresh_kampus: Button
    private lateinit var list_ptn: RecyclerView
    private lateinit var adapter: KampusListAdapter

    private var ptnList: MutableList<PTN> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_kampus))
        val v = inflater.inflate(R.layout.fragment_kampus, container, false)
        swiperefresh_ptn = v.findViewById(R.id.swiperefresh_ptn)
        list_ptn = v.findViewById(R.id.rv_list_ptn)
        btn_refresh_kampus = v.findViewById(R.id.btn_refresh_kampus)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        KampusPresenter.getListPTN(this)
        adapter = KampusListAdapter(ptnList) {
            activity?.startActivity<DetailKampusActivity>("kode" to "${it.getKODE()}")
        }
        list_ptn.layoutManager = LinearLayoutManager(activity)
        list_ptn.adapter = adapter
        swiperefresh_ptn.onRefresh {
            KampusPresenter.getListPTN(this)
        }
        btn_refresh_kampus.setOnClickListener {
            btn_refresh_kampus.visibility = View.GONE
            KampusPresenter.getListPTN(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showLoading() {
        swiperefresh_ptn.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_ptn.isRefreshing = false
    }

    override fun showPtnList(data: List<PTN>) {
        swiperefresh_ptn.isRefreshing = false
        ptnList.clear()
        ptnList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showError() {
        Log.d("TAGERROR", "ERROR")
        swiperefresh_ptn.isRefreshing = false
        btn_refresh_kampus.visibility = View.VISIBLE
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0!!.isNotEmpty()) {
            KampusPresenter.getSearchPTN(this, p0)
        } else {
            KampusPresenter.getListPTN(this)
        }
        return false
    }
}
