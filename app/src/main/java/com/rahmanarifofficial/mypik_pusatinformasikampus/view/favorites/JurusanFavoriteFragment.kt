package com.rahmanarifofficial.mypik_pusatinformasikampus.view.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.FavoriteJurusanListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.JurusanDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.database
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend.DetailJurusanActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class JurusanFavoriteFragment : androidx.fragment.app.Fragment() {
    private var favoritesJurusan = mutableListOf<JurusanDB>()
    private lateinit var rv_list_jurusan_favorite: androidx.recyclerview.widget.RecyclerView
    private lateinit var adapter: FavoriteJurusanListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_jurusan_favorite, container, false)
        rv_list_jurusan_favorite = v.findViewById(R.id.rv_list_jurusan_favorite)
        rv_list_jurusan_favorite.setHasFixedSize(true)
        rv_list_jurusan_favorite.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adapter = FavoriteJurusanListAdapter(favoritesJurusan) {
            activity?.startActivity<DetailJurusanActivity>("kode" to "${it.idJurusan}")
        }
        rv_list_jurusan_favorite.adapter = adapter
        showFavorite()
        return v
    }

    private fun showFavorite() {
        favoritesJurusan.clear()
        context?.database?.use {
            val result = select(JurusanDB.TABLE_JURUSAN)
            val favorite = result.parseList(classParser<JurusanDB>())
            favoritesJurusan.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}
