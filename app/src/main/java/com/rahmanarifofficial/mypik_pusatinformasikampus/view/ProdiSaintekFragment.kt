package com.rahmanarifofficial.mypik_pusatinformasikampus.view

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.ProdiKampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.KampusPresenter
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.support.v4.toast
import java.text.DecimalFormat

class ProdiSaintekFragment : Fragment(), ProdiKampusView {

    private lateinit var list_prodi_ptn: RecyclerView
    private lateinit var adapter: ProdiKampusListAdapter
    private lateinit var tvPTN: TextView
    private lateinit var tvProdi: TextView
    private lateinit var tvTipe: TextView
    private lateinit var tvAkreditasi: TextView
    private lateinit var tvKuota: TextView
    private lateinit var tvKuota2018: TextView
    private lateinit var tvPersentase: TextView
    private lateinit var tvPortofolio: TextView

    private var prodiList: MutableList<Prodi> = mutableListOf()

    companion object {
        fun saintekIntance(kode: String): ProdiSaintekFragment {
            val fragment = ProdiSaintekFragment()
            val args = Bundle()
            args.putString("kode", kode)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_prodi_saintek, container, false)
        list_prodi_ptn = v.findViewById(R.id.rv_list_prodi_saintek)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val kode = arguments?.getString("kode")
        KampusPresenter.getProdiList(this, kode!!, "'saintek'")
        adapter = ProdiKampusListAdapter(prodiList) {
            KampusPresenter.getDetailProdi(this, "${it.getKodeProdi()}")
            val view = layoutInflater.inflate(R.layout.dialog_detail_prodi, null)
            tvProdi = view.findViewById(R.id.tv_nama_prodi_detail_prodi)
            tvPTN = view.findViewById(R.id.tv_nama_ptn_detail_prodi)
            tvPortofolio = view.findViewById(R.id.tv_jenis_portofolio_detail_prodi)
            tvKuota2018 = view.findViewById(R.id.tv_daya_tampung_2018_detail_prodi)
            tvKuota = view.findViewById(R.id.tv_daya_tampung_detail_prodi)
            tvAkreditasi = view.findViewById(R.id.tv_akreditasi_prodi_detail_prodi)
            tvTipe = view.findViewById(R.id.tv_tipe_prodi_detail_prodi)
            tvPersentase = view.findViewById(R.id.tv_persentase_kursi_detail_prodi)
            val dialog = BottomSheetDialog(activity!!)
            dialog.setContentView(view)
            dialog.show()
        }
        list_prodi_ptn.layoutManager = LinearLayoutManager(activity)
        list_prodi_ptn.adapter = adapter
    }

    override fun showProdi(data: List<Prodi>) {
        prodiList.clear()
        prodiList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showDetailProdi(data: List<Prodi>) {
        val kuota = data[0].getDayaTampung2019()!!.toDouble()
        val kuota2018 = data[0].getPeminat2018()!!.toDouble()
        val persentase = DecimalFormat("#.##").format(kuota / kuota2018 * 100)

        var portofolio = data[0].getJenisPortofolio()
        when (portofolio) {
            "1" -> portofolio = "Olahraga"
            "2" -> portofolio = "Seni Rupa Dasar"
            "3" -> portofolio = "Tari"
            "4" -> portofolio = "Musik"
            "5" -> portofolio = "Teater"
            "6" -> portofolio = "Film/Televisi"
            "7" -> portofolio = "Fotografi"
            "8" -> portofolio = "Karawitan"
            "9" -> portofolio = "Etnomusikalagi"
        }

        tvProdi.text = data[0].getNamaProdi()
        tvPTN.text = data[0].getNamaUniversitas()
        tvTipe.text = data[0].getTipe()
        tvAkreditasi.text = data[0].getAkreditasi()
        tvKuota.text = data[0].getDayaTampung2019()
        tvKuota2018.text = data[0].getPeminat2018()
        tvPortofolio.text = portofolio
        tvPersentase.text = persentase + " %"
    }

    override fun showError(data: String) {
        toast(data)
    }
}
