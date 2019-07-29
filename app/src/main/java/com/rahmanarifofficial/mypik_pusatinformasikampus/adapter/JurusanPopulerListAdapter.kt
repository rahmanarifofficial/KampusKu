package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Jurusan
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_populer_jurusan.view.*

class JurusanPopulerListAdapter(private var jurusan: List<Jurusan>, private val listener: (Jurusan) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<JurusanPopulerListAdapter.JurusanViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): JurusanViewHolder {
        return JurusanViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_populer_jurusan, p0, false))
    }

    override fun getItemCount(): Int {
        return jurusan.size
    }

    override fun onBindViewHolder(p0: JurusanViewHolder, p1: Int) {
        return p0.bindJurusan(jurusan[p1], listener)
    }

    class JurusanViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bindJurusan(jurusan: Jurusan, listener: (Jurusan) -> Unit) {
            itemView.tv_nama_jurusan_populer.text = jurusan.jurusan
            itemView.tv_kelompok_jurusan_populer.text = jurusan.namaKelompok
            itemView.tv_mapel_jurusan_populer.text = jurusan.mapel
            itemView.tv_tipe_jurusan_populer.text = jurusan.tipe
            itemView.tv_popularitas_jurusan.text = jurusan.isPopuler
            if (!jurusan.fotoBanner.isNullOrEmpty()) {
                Picasso.get()
                    .load(jurusan.fotoBanner!!)
                    .centerCrop()
                    .fit()
                    .placeholder(R.drawable.ic_university_campus)
                    .into(itemView.iv_foto_jurusan)
            }
            itemView.setOnClickListener {
                listener(jurusan)
            }
        }
    }
}