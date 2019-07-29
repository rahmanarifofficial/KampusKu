package com.rahmanarifofficial.mypik_pusatinformasikampus.view.favorites

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.adapter.FavoritePagerAdapter
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        supportActionBar?.title = getString(R.string.favorites)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        setupViewPager(view_pager_favorite)
        tab_favorite.setupWithViewPager(view_pager_favorite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewPager(pager: androidx.viewpager.widget.ViewPager) {
        val adapter = FavoritePagerAdapter(supportFragmentManager)

        val kampus = KampusFavoriteFragment()
        adapter.addFragment(kampus, "KAMPUS")

        val jurusan = JurusanFavoriteFragment()
        adapter.addFragment(jurusan, "JURUSAN")

        val beasiswa = BeasiswaFavoritesFragment()
        adapter.addFragment(beasiswa, "BEASISWA")

        pager.adapter = adapter
    }

}
