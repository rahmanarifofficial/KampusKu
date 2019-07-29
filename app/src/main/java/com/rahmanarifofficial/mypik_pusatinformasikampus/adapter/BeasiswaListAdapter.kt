package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Beasiswa
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_beasiswa_active.view.*
import kotlinx.android.synthetic.main.item_beasiswa_inactive.view.*

class BeasiswaListAdapter(private var beasiswa: List<Beasiswa>, private val listener: (Beasiswa) -> Unit) :
    RecyclerView.Adapter<BeasiswaListAdapter.BeasiswaViewHolder>() {

    companion object {
        val BEASISWA_ACTIVE_CODE = 1
        val BEASISWA_INACTIVE_CODE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeasiswaViewHolder {
        return BeasiswaListAdapter.BeasiswaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_beasiswa_active,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return beasiswa.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(p0: BeasiswaViewHolder, p1: Int) {
        return p0.bindBeasiswa(beasiswa[p1], listener)
    }

    class BeasiswaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindBeasiswa(beasiswa: Beasiswa, listener: (Beasiswa) -> Unit) {
            itemView.tv_nama_beasiswa.text = beasiswa.beasiswa
            itemView.tv_nama_penyelenggara_beasiswa.text = beasiswa.penyelenggara
            itemView.tv_deadline_beasiswa.text = beasiswa.deadline
            itemView.tv_jenis_beasiswa.text = beasiswa.jenisPembiayaan
            itemView.tv_kategori_beasiswa.text = beasiswa.kategori
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