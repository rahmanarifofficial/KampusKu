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
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.PtnListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.PtnPresenter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class KampusFragment : Fragment(), PtnView {
    private lateinit var pb_ptn: ProgressBar
    private lateinit var swiperefresh_ptn: SwipeRefreshLayout
    private lateinit var list_ptn: RecyclerView
    private lateinit var adapter: PtnListAdapter

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
        PtnPresenter.getListPTN(this)
        adapter = PtnListAdapter(ptnList) {
            activity?.startActivity<DetailPTNActivity>()
        }
        list_ptn.layoutManager = LinearLayoutManager(activity)
        list_ptn.adapter = adapter
//        loadPTN()
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
        Log.d("TESTAPI", data.get(0).getNAMA())
    }

    override fun showError(error: String) {
        toast(error)
    }


//    private fun loadPTN() {
//        val apiService = ApiBuilder.getClient()?.create(ApiService::class.java)
//        val call = apiService?.getListPTN()
//        call?.enqueue(this)
//    }
//
//    override fun onFailure(call: Call<List<PTN>>, t: Throwable) {
//        Log.d("TESTAPI", "Message " + t.message)
//        Log.d("TESTAPI", "Loc Mess " + t.localizedMessage)
//    }
//
//    override fun onResponse(call: Call<List<PTN>>, response: Response<List<PTN>>) {
//        Log.d("TESTAPI", "TEST X")
//        Log.d("TESTAPI", response.body()?.size.toString())
//        for (ptn in response.body()!!.indices) {
//            Log.d("TESTAPI", "test " + response.body()!![ptn].getNAMA())
//        }
}
