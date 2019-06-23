package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ptn.view.*

class JurusanKampusListAdapter(private var prodi: List<Prodi>, private val listener: (Prodi) -> Unit) :
    RecyclerView.Adapter<JurusanKampusListAdapter.PtnViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PtnViewHolder {
        return PtnViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ptn, parent, false))
    }

    override fun getItemCount(): Int {
        return prodi.size
    }

    override fun onBindViewHolder(p0: PtnViewHolder, p1: Int) {
        return p0.bindPtn(prodi[p1], listener)
    }

    class PtnViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindPtn(prodi: Prodi, listener: (Prodi) -> Unit) {
            itemView.tv_ptn.text = prodi.getNamaUniversitas()
            itemView.tv_link_ptn.text = prodi.getLinkPMB()
            itemView.tv_akreditasi_ptn.text = prodi.getAkreditasi()
            Picasso.get()
                .load(prodi.getLinkLogo())
                .placeholder(R.drawable.ic_university_campus)
                .centerCrop()
                .fit()
                .into(itemView.iv_foto_ptn)
            itemView.setOnClickListener {
                listener(prodi)
            }
        }
    }
}