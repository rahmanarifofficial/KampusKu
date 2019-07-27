package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.BeasiswaDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.TAG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_beasiswa_active.view.*

class FavoriteBeasiswaListAdapter(private var beasiswa: List<BeasiswaDB>, private val listener: (BeasiswaDB) -> Unit) :
    RecyclerView.Adapter<FavoriteBeasiswaListAdapter.BeasiswaViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BeasiswaViewHolder {
        return FavoriteBeasiswaListAdapter.BeasiswaViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_beasiswa_active,
                p0,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return beasiswa.size
    }

    override fun onBindViewHolder(p0: BeasiswaViewHolder, p1: Int) {
        return p0.bindBeasiswa(beasiswa[p1], listener)
    }

    class BeasiswaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindBeasiswa(beasiswa: BeasiswaDB, listener: (BeasiswaDB) -> Unit) {
            itemView.tv_nama_beasiswa.text = beasiswa.beasiswa
            itemView.tv_nama_penyelenggara_beasiswa.text = beasiswa.linkBanner
            itemView.tv_deadline_beasiswa.text = beasiswa.deadline
            itemView.tv_jenis_beasiswa.text = beasiswa.kategori
            itemView.tv_kategori_beasiswa.text = beasiswa.jenisPembiayaan
            if (!beasiswa.linkBanner.isNullOrEmpty()) {
                Picasso.get()
                    .load(beasiswa.linkBanner)
                    .placeholder(R.drawable.ic_scholars)
                    .centerCrop()
                    .fit()
                    .into(itemView.iv_foto_banner_beasiswa)
            }
            itemView.setOnClickListener {
                listener(beasiswa)
            }
        }
    }
}