package com.rahmanarifofficial.mypik_pusatinformasikampus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.KampusFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_menu.inflateMenu(R.menu.menu_bottom_navigation);
        fragmentManager = supportFragmentManager;

        fragmentManager.beginTransaction().replace(R.id.main_container, KampusFragment()).commit()

    }
}
