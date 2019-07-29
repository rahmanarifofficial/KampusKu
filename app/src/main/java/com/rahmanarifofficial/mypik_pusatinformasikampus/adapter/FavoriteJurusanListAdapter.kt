package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.JurusanDB
import kotlinx.android.synthetic.main.item_jurusan.view.*

class FavoriteJurusanListAdapter(private val jurusanList: List<JurusanDB>, private val listener: (JurusanDB) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<FavoriteJurusanListAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteJurusanListAdapter.FavoriteViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_jurusan,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int = jurusanList.size

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindJurusan(jurusanList[p1], listener)
    }

    class FavoriteViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bindJurusan(jurusan: JurusanDB, listener: (JurusanDB) -> Unit) {
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