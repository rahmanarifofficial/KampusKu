package com.rahmanarifofficial.mypik_pusatinformasikampus.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity

import com.rahmanarifofficial.mypik_pusatinformasikampus.R

class BeasiswaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_beasiswa))
        return inflater.inflate(R.layout.fragment_beasiswa, container, false)
    }

}
