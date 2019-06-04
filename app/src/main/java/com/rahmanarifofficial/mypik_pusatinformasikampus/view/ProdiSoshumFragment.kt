package com.rahmanarifofficial.mypik_pusatinformasikampus.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.ProdiKampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter

class ProdiSoshumFragment : Fragment(), ProdiKampusView {

    private lateinit var list_prodi_ptn: RecyclerView
    private lateinit var adapter: ProdiKampusListAdapter

    private var prodiList: MutableList<Prodi> = mutableListOf()

    companion object {
        fun soshumIntance(kode: String): ProdiSoshumFragment {
            val fragment = ProdiSoshumFragment()
            val args = Bundle()
            args.putString("kode", kode)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_prodi_soshum, container, false)
        list_prodi_ptn = v.findViewById(R.id.rv_list_prodi_soshum)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val kode = arguments?.getString("kode")
        KampusPresenter.getProdiList(this, kode!!, "'soshum'")
        adapter = ProdiKampusListAdapter(prodiList) {

        }
        list_prodi_ptn.layoutManager = LinearLayoutManager(activity)
        list_prodi_ptn.adapter = adapter
    }

    override fun showProdi(data: List<Prodi>) {
        prodiList.clear()
        prodiList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showError(data: String) {
        Log.d("TESTAPI", data)
    }

}
