package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import kotlinx.android.synthetic.main.item_prodi.view.*

class ProdiKampusListAdapter(private var prodi: List<Prodi>, private val listener: (Prodi) -> Unit) :
    RecyclerView.Adapter<ProdiKampusListAdapter.ProdiKampusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdiKampusViewHolder {
        return ProdiKampusListAdapter.ProdiKampusViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_prodi,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return prodi.size
    }

    override fun onBindViewHolder(p0: ProdiKampusViewHolder, p1: Int) {
        return p0.bindProdi(prodi[p1], listener)
    }

    class ProdiKampusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindProdi(prodi: Prodi, listener: (Prodi) -> Unit) {
            itemView.tv_prodi_detail_ptn.text = prodi.getNamaProdi()
            itemView.tv_kuota_prodi_detail_ptn.text = prodi.getDayaTampung2019()
            itemView.tv_akreditasi_prodi_detail_ptn.text = prodi.getAkreditasi()
            val kode = prodi.getKodeProdi()
            itemView.setOnClickListener {
                listener(prodi)
            }
        }
    }
}