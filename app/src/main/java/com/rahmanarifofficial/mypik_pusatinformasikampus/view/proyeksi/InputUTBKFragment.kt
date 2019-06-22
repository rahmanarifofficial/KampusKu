package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity

import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.ProyeksiPresenter
import kotlinx.android.synthetic.main.dialog_pilih_kampus.*
import kotlinx.android.synthetic.main.fragment_input_utbk.*

class InputUTBKFragment : Fragment(), AdapterView.OnItemSelectedListener, InputUTBKView {

    private val kelompok = arrayOf("Saintek", "Soshum")
    private val ptnList1: MutableList<String> = mutableListOf()
    private val ptnList2: MutableList<String> = mutableListOf()

    private lateinit var map: HashMap<String, String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_input))
        return inflater.inflate(R.layout.fragment_input_utbk, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinadapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_dropdown_item, kelompok)
        spinner_pilihan.adapter = spinadapter
        spinner_pilihan.setOnItemSelectedListener(this)

        ProyeksiPresenter.getListPTN(this)

        map = HashMap()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_pilihan -> {
                when (position) {
                    0 -> {
                        layout_nilai_saintek.visibility = View.VISIBLE
                        layout_nilai_soshum.visibility = View.GONE
                    }
                    1 -> {
                        layout_nilai_saintek.visibility = View.GONE
                        layout_nilai_soshum.visibility = View.VISIBLE
                    }
                }
            }
            R.id.spinner_pilihan_kampus_1 -> {
                spinner_pilihan_jurusan_1.visibility = View.VISIBLE

            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        layout_nilai_saintek.visibility = View.VISIBLE
        layout_nilai_soshum.visibility = View.GONE
    }

    override fun showPTNList(data: List<PTN>?) {
        ptnList2.add("TIDAK ADA")
        if (!data.isNullOrEmpty()) {
            for (list in data) {
                ptnList1.add(list.getNAMA()!!)
                ptnList2.add(list.getNAMA()!!)
                map.put(list.getKODE()!!, list.getNAMA()!!)
            }
        }
        val ptnadapter1 = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_dropdown_item, ptnList1)
        val ptnadapter2 = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_dropdown_item, ptnList2)
        spinner_pilihan_kampus_1.adapter = ptnadapter1
        spinner_pilihan_kampus_1.setOnItemSelectedListener(this)
        spinner_pilihan_kampus_2.adapter = ptnadapter2
        spinner_pilihan_kampus_2.setOnItemSelectedListener(this)
    }

    override fun showProdiList(data: List<Prodi>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
    }

}
