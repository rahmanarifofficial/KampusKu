package com.rahmanarifofficial.mypik_pusatinformasikampus.view.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.FavoriteKampusListAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.KampusDB
import com.rahmanarifofficial.mypik_pusatinformasikampus.db.database
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.DetailKampusActivity
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class KampusFavoriteFragment : Fragment() {

    private var favoritesKampus = mutableListOf<KampusDB>()
    private lateinit var rv_list_kampus_favorite: RecyclerView
    private lateinit var adapter: FavoriteKampusListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_kampus_favorite, container, false)
        rv_list_kampus_favorite = v.findViewById(R.id.rv_list_kampus_favorite)
        rv_list_kampus_favorite.setHasFixedSize(true)
        rv_list_kampus_favorite.layoutManager = LinearLayoutManager(activity)
        adapter = FavoriteKampusListAdapter(favoritesKampus) {
            activity?.startActivity<DetailKampusActivity>("kode" to "${it.kode}")
        }
        rv_list_kampus_favorite.adapter = adapter
        showFavorite()
        return v
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(KampusDB.TABLE_KAMPUS)
            val favorite = result.parseList(classParser<KampusDB>())
            favoritesKampus.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
