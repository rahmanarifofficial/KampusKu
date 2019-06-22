package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_jurusan.view.*

class JurusanListAdapter(private var jurusan: List<Jurusan>, private val listener: (Jurusan) -> Unit) :
    RecyclerView.Adapter<JurusanListAdapter.JurusanViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): JurusanViewHolder {
        return JurusanViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_jurusan, p0, false))
    }

    override fun getItemCount(): Int {
        return jurusan.size
    }

    override fun onBindViewHolder(p0: JurusanViewHolder, p1: Int) {
        return p0.bindJurusan(jurusan[p1], listener)
    }

    class JurusanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindJurusan(jurusan: Jurusan, listener: (Jurusan) -> Unit) {
            itemView.tv_nama_jurusan.text = jurusan.jurusan
            itemView.tv_kelompok_jurusan.text = jurusan.namaKelompok
            itemView.tv_mapel_jurusan.text = jurusan.mapel
            itemView.tv_tipe_jurusan.text = jurusan.tipe
            itemView.setOnClickListener {
                listener(jurusan)
            }
        }
    }
}