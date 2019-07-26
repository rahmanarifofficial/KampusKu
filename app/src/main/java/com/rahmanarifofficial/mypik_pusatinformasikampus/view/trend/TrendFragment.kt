package com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.*
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.JurusanPopulerListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.TrendPresenter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh


class TrendFragment : Fragment(), TrendView, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {

    private lateinit var adapter: JurusanPopulerListAdapter
    private lateinit var adapterJurusan: JurusanListAdapter
    private lateinit var swiperefresh_trend: SwipeRefreshLayout
    private lateinit var btn_refresh_trend: Button
    private lateinit var rv_list_jurusan: RecyclerView
    private lateinit var rv_list_jurusan_populer: RecyclerView
    private lateinit var spinner_kelompok_jurusan: Spinner

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
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_trend))
        val v = inflater.inflate(R.layout.fragment_trend, container, false)
        swiperefresh_trend = v.findViewById(R.id.swiperefresh_trend)
        btn_refresh_trend = v.findViewById(R.id.btn_refresh_trend)
        rv_list_jurusan = v.findViewById(R.id.rv_list_jurusan)
        rv_list_jurusan_populer = v.findViewById(R.id.rv_list_jurusan_populer)
        spinner_kelompok_jurusan = v.findViewById(R.id.spinner_kelompok_jurusan)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        TrendPresenter.showPopulerJurusan(this)
        adapter = JurusanPopulerListAdapter(jurusanPopulerList) {
            activity?.startActivity<DetailJurusanActivity>("kode" to "${it.id}")
        }
        rv_list_jurusan_populer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_list_jurusan_populer.adapter = adapter
        swiperefresh_trend.onRefresh {
            TrendPresenter.showPopulerJurusan(this)
        }
        val spinadapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_dropdown_item, kelompok)
        spinner_kelompok_jurusan.adapter = spinadapter
        spinner_kelompok_jurusan.setOnItemSelectedListener(this)
        adapterJurusan = JurusanListAdapter(jurusanList) {
            activity?.startActivity<DetailJurusanActivity>("kode" to "${it.id}")
        }
        rv_list_jurusan.layoutManager = LinearLayoutManager(activity)
        rv_list_jurusan.adapter = adapterJurusan
        btn_refresh_trend.setOnClickListener {
            btn_refresh_trend.visibility = View.GONE
            TrendPresenter.showPopulerJurusan(this)
            spinner_kelompok_jurusan.setOnItemSelectedListener(this)
        }
    }

    override fun showLoading() {
        swiperefresh_trend.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh_trend.isRefreshing = false
    }

    override fun showPopulerJurusan(data: List<Jurusan>) {
        swiperefresh_trend.isRefreshing = false
        if (!data.isNullOrEmpty()) {
            jurusanPopulerList.clear()
            jurusanPopulerList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showJurusanList(data: List<Jurusan>) {
        jurusanList.clear()
        jurusanList.addAll(data)
        adapterJurusan.notifyDataSetChanged()
    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
        swiperefresh_trend.isRefreshing = false
        btn_refresh_trend.visibility = View.VISIBLE
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TrendPresenter.getListJurusan(1, this)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TrendPresenter.getListJurusan(position + 1, this);
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_jurusan)
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0!!.isNotEmpty()) {
            TrendPresenter.searchJurusan(p0, this)
        } else {
            TrendPresenter.getListJurusan(1, this)
        }
        return false
    }

}
