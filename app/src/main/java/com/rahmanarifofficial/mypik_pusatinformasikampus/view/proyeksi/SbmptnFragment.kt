package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.LoginPreferences
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun.AuthentikasiFragment
import kotlinx.android.synthetic.main.fragment_sbmptn.*

class SbmptnFragment : Fragment() {

    private lateinit var inputNilaiDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_proyeksi))
        return inflater.inflate(R.layout.fragment_sbmptn, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val prefs = LoginPreferences(activity!!)
        inputNilaiDialog = Dialog(activity!!)

        btnInputUTBK.setOnClickListener {
            if (prefs.getWasLogin()) {
                updateUI(InputUTBKFragment())
            } else {
                updateUI(AuthentikasiFragment())
            }
        }

        btnLihatProyeksi.setOnClickListener {
            if (prefs.getWasLogin()) {
                updateUI(HasilProyeksiFragment())
            } else {
                updateUI(AuthentikasiFragment())
            }
        }
    }

    fun updateUI(fragment: Fragment) {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        ft.replace(R.id.main_container, fragment)
        ft.commit()
    }
}
