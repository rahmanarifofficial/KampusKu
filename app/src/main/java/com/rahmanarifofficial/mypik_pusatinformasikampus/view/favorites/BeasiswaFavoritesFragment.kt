package com.rahmanarifofficial.mypik_pusatinformasikampus.view.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.FavoriteBeasiswaListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.BeasiswaDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.JurusanDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.database
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa.DetailBeasiwaActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class BeasiswaFavoritesFragment : Fragment() {
    private var favoritesJurusan = mutableListOf<BeasiswaDB>()
    private lateinit var rv_list_beasiswa_favorite: RecyclerView
    private lateinit var adapter: FavoriteBeasiswaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_beasiswa_favorites, container, false)
        rv_list_beasiswa_favorite = v.findViewById(R.id.rv_list_beasiswa_favorite)
        rv_list_beasiswa_favorite.setHasFixedSize(true)
        rv_list_beasiswa_favorite.layoutManager = LinearLayoutManager(activity)
        adapter = FavoriteBeasiswaListAdapter(favoritesJurusan) {
            activity?.startActivity<DetailBeasiwaActivity>("kode" to "${it.idBeasiswa}")
        }
        rv_list_beasiswa_favorite.adapter = adapter
        showFavorite()

        return v
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(BeasiswaDB.TABLE_BEASISWA)
            val favorite = result.parseList(classParser<BeasiswaDB>())
            favoritesJurusan.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
