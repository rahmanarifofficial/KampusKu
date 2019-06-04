package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ptn.view.*

class KampusListAdapter(private var ptn: List<PTN>, private val listener: (PTN) -> Unit) :
    RecyclerView.Adapter<KampusListAdapter.PtnViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PtnViewHolder {
        return PtnViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ptn, parent, false))
    }

    override fun getItemCount(): Int {
        return ptn.size
    }

    override fun onBindViewHolder(p0: PtnViewHolder, p1: Int) {
        return p0.bindPtn(ptn[p1], listener)
    }

    class PtnViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindPtn(ptn: PTN, listener: (PTN) -> Unit) {
            itemView.tv_ptn.text = ptn.getNAMA()
            itemView.tv_link_ptn.text = ptn.getLINKPMB()
            itemView.tv_akreditasi_ptn.text = ptn.getAKREDITASI()
            Picasso.get()
                .load(ptn.getLINKLOGO())
                .placeholder(R.drawable.ic_university_campus)
                .centerCrop()
                .fit()
                .into(itemView.iv_foto_ptn)
            itemView.setOnClickListener {
                listener(ptn)
            }
        }
    }
}