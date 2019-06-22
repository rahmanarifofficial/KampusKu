package com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity

import com.rahmanarifofficial.mypik_pusatinformasikampus.R

class HasilProyeksiFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_hasil_proyeksi))
        return inflater.inflate(R.layout.fragment_hasil_proyeksi, container, false)
    }


}
