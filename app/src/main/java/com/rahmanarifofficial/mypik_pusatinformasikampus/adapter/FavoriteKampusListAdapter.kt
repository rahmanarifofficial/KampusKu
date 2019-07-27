package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.KampusDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ptn.view.*

class FavoriteKampusListAdapter(private val kampusList: List<KampusDB>, private val listener: (KampusDB) -> Unit) :
    RecyclerView.Adapter<FavoriteKampusListAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_ptn, p0, false))
    }

    override fun getItemCount(): Int = kampusList.size

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindKampus(kampusList[p1], listener)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindKampus(kampus: KampusDB, listener: (KampusDB) -> Unit) {
            itemView.tv_ptn.text = kampus.nama
            itemView.tv_link_ptn.text = kampus.akreditasi
            itemView.tv_akreditasi_ptn.text = kampus.linkLogo
            Picasso.get()
                .load(kampus.linkPMB)
                .placeholder(R.drawable.ic_university_campus)
                .centerCrop()
                .fit()
                .into(itemView.iv_foto_ptn)
            itemView.setOnClickListener {
                listener(kampus)
            }
        }
    }
}