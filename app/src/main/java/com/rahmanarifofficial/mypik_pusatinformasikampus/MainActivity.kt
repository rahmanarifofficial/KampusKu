package com.rahmanarifofficial.mypik_pusatinformasikampus

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.inflateMenu(R.menu.menu_bottom_navigation);
        fragmentManager = supportFragmentManager;

        fragmentManager.beginTransaction().replace(R.id.main_container, KampusFragment()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId
            when (id) {
                R.id.kampus_menu -> fragment = KampusFragment()
                R.id.tren_menu -> fragment = TrendFragment()
                R.id.beasiswa_menu -> fragment = BeasiswaFragment()
                R.id.sbmptn_menu -> fragment = SbmptnFragment()
                R.id.akun_menu -> fragment = AkunFragment()
            }
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, fragment)
            transaction.commit()
            true
        }
    }
}
