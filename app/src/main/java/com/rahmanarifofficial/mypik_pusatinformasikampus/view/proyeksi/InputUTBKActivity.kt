package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Nilai
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.PTN
import com.rahmanarifofficial.mypik_pusatinformasikampus.model.Prodi
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.ProyeksiPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import kotlinx.android.synthetic.main.activity_input_utbk.*
import kotlinx.android.synthetic.main.dialog_input_nilai_saintek.*
import kotlinx.android.synthetic.main.dialog_input_nilai_soshum.*
import kotlinx.android.synthetic.main.dialog_input_nilai_tps.*
import kotlinx.android.synthetic.main.dialog_pilih_kampus.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class InputUTBKActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, InputUTBKView {

    private lateinit var ptnadapter: ArrayAdapter<String>
    private lateinit var jurusanadapter: ArrayAdapter<String>
    private lateinit var idPengguna: String

    private val kelompok = arrayOf("Saintek", "Soshum")
    private val ptnList1: MutableList<String> = mutableListOf()
    private val jurusanList1: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_utbk)
        supportActionBar?.title = getString(R.string.text_input)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spinadapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, kelompok)
        spinner_pilihan.adapter = spinadapter
        spinner_pilihan.setOnItemSelectedListener(this)

        ProyeksiPresenter.getListPTN(this)
        val prefs = AuthPreferences(this)
        idPengguna = prefs.getIdPengguna()

        ProyeksiPresenter.getNilai(this, idPengguna)

        swiperefresh_utbk.onRefresh {
            ProyeksiPresenter.getListPTN(this)
            ProyeksiPresenter.getNilai(this, idPengguna)
        }
//        btnSubmitNilai.setOnClickListener {
//            ProyeksiPresenter.insertNilai(this, )
//        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_pilihan -> {
                when (position) {
                    0 -> {
                        layout_nilai_saintek.visibility = View.VISIBLE
                        layout_nilai_soshum.visibility = View.GONE
                    }
                    1 -> {
                        layout_nilai_saintek.visibility = View.GONE
                        layout_nilai_soshum.visibility = View.VISIBLE
                    }
                }
            }
            R.id.spinner_pilihan_kampus_1 -> {
                spinner_pilihan_jurusan_1.visibility = View.VISIBLE
                ProyeksiPresenter.getProdiByPTN(this, ptnadapter.getItem(position)!!)

            }
            R.id.spinner_pilihan_jurusan_1 -> {
                ProyeksiPresenter.getKodeProdi(
                    this,
                    spinner_pilihan_kampus_1.selectedItem.toString(),
                    spinner_pilihan_jurusan_1.selectedItem.toString()
                )
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        layout_nilai_saintek.visibility = View.VISIBLE
        layout_nilai_soshum.visibility = View.GONE
    }

    override fun showProgress() {
        pb_jurusan.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_jurusan.visibility = View.INVISIBLE
    }

    override fun showPTNList(data: List<PTN>) {
        swiperefresh_utbk.isRefreshing = false
        if (!data.isNullOrEmpty()) {
            for (list in data) {
                ptnList1.add(list.getNAMA()!!)
            }
        }
        ptnadapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ptnList1)
        ptnadapter.notifyDataSetChanged()
        spinner_pilihan_kampus_1.adapter = ptnadapter
        spinner_pilihan_kampus_1.setOnItemSelectedListener(this)
    }

    override fun showProdiList(data: List<Prodi>, kode: Int) {
        if (kode == 1) {
            jurusanList1.clear()
            if (!data.isNullOrEmpty()) {
                for (list in data) {
                    jurusanList1.add(list.getNamaProdi()!!)
                }
            }
            jurusanadapter =
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, jurusanList1)
            jurusanadapter.notifyDataSetChanged()
            spinner_pilihan_jurusan_1.adapter = jurusanadapter
            spinner_pilihan_jurusan_1.setOnItemSelectedListener(this)
        } else if (kode == 2) {
            val kode_prodi = data[0].getKodeProdi()
            Log.d("TAGERROR", "Kode Prodi " + kode_prodi)
            btnSubmitNilai.setOnClickListener {
                ProyeksiPresenter.insertNilai(
                    this,
                    idPengguna,
                    et_nilai_kp.text.toString(),
                    et_nilai_pk.text.toString(),
                    et_nilai_ppu.text.toString(),
                    et_nilai_kmbm.text.toString(),
                    et_nilai_ms.text.toString(),
                    et_nilai_f.text.toString(),
                    et_nilai_k.text.toString(),
                    et_nilai_b.text.toString(),
                    et_nilai_ms_sos.text.toString(),
                    et_nilai_g.text.toString(),
                    et_nilai_sejarah.text.toString(),
                    et_nilai_s.text.toString(),
                    et_nilai_e.text.toString(),
                    spinner_pilihan_kampus_1.selectedItem.toString(),
                    spinner_pilihan_jurusan_1.selectedItem.toString()
                )
                toast("Terimakasih Data Akan Diproses Kami akan mengirimkan hasilnya via email yang telah didaftarkan")
            }
        }
    }

    override fun showError(data: String) {
        swiperefresh_utbk.isRefreshing = false
    }

    override fun showNilai(data: List<Nilai>) {
        if (!data.isNullOrEmpty()) {
            et_nilai_kp.setText(data[0].nilaiPenalaranUmum)
            et_nilai_pk.setText(data[0].nilaiPengetahuanKuantitatif)
            et_nilai_ppu.setText(data[0].nilaiPengetahuanUmum)
            et_nilai_kmbm.setText(data[0].nilaiMemahamiBacaan)
            et_nilai_ms.setText(data[0].nilaiMatSaintek)
            et_nilai_f.setText(data[0].nilaiFisika)
            et_nilai_k.setText(data[0].nilaiKimia)
            et_nilai_b.setText(data[0].nilaiBiologi)
            et_nilai_ms_sos.setText(data[0].nilaiMatSoshum)
            et_nilai_g.setText(data[0].nilaiGeografi)
            et_nilai_sejarah.setText(data[0].nilaiSejarah)
            et_nilai_s.setText(data[0].nilaiSosiologi)
            et_nilai_e.setText(data[0].nilaiEkonomi)
        }
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

}
