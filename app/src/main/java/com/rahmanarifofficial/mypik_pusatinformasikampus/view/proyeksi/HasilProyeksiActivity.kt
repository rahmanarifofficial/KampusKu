package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Nilai
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.ProyeksiPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import kotlinx.android.synthetic.main.activity_hasil_proyeksi.*

class HasilProyeksiActivity : AppCompatActivity(), HasilProyeksiView {

    private lateinit var idPengguna: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_proyeksi)
        supportActionBar?.title = getString(R.string.text_hasil_proyeksi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val prefs = AuthPreferences(this)
        idPengguna = prefs.getIdPengguna()
        ProyeksiPresenter.getNilai(this, idPengguna)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress() {
        swiperefresh_hasil_proyeksi.isRefreshing = true
    }

    override fun hideProgress() {
        swiperefresh_hasil_proyeksi.isRefreshing = false
    }

    override fun showError(data: String) {
        Log.d("TAGERROR", data)
    }

    override fun showNilai(data: List<Nilai>) {
        if (!data.isNullOrEmpty()) {
            tv_kampus_1.text = data[0].namaPtn + "-" + data[0].namaProdi
        }
    }

}
