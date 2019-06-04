package com.rahmanarifofficial.mypik_pusatinformasikampus.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.KampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class KampusFragment : Fragment(), KampusView {
    private lateinit var pb_ptn: ProgressBar
    private lateinit var swiperefresh_ptn: SwipeRefreshLayout
    private lateinit var list_ptn: RecyclerView
    private lateinit var adapter: KampusListAdapter

    private var ptnList: MutableList<PTN> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_kampus, container, false)
        pb_ptn = v.findViewById(R.id.pb_ptn)
        swiperefresh_ptn = v.findViewById(R.id.swiperefresh_ptn)
        list_ptn = v.findViewById(R.id.rv_list_ptn)

        return v;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        KampusPresenter.getListPTN(this)
        adapter = KampusListAdapter(ptnList) {
            activity?.startActivity<DetailKampusActivity>("kode" to "${it.getKODE()}")
        }
        list_ptn.layoutManager = LinearLayoutManager(activity)
        list_ptn.adapter = adapter
        swiperefresh_ptn.onRefresh {
            KampusPresenter.getListPTN(this)
        }
    }

    override fun showLoading() {
        pb_ptn.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_ptn.visibility = View.INVISIBLE
    }

    override fun showPtnList(data: List<PTN>) {
        swiperefresh_ptn.isRefreshing = false
        ptnList.clear()
        ptnList.addAll(data)
        adapter.notifyDataSetChanged()
        Log.d("TESTAPI", data.get(0).getWORLDRANK())
    }

    override fun showError(error: String) {
        toast(error)
        KampusPresenter.getListPTN(this)
    }
}
