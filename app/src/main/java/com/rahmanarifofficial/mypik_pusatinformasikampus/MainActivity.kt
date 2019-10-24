package com.rahmanarifofficial.mypik_pusatinformasikampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.onesignal.OneSignal
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun.AuthentikasiFragment
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun.ProfileFragment
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.beasiswa.BeasiswaFragment
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.kampus.KampusFragment
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.proyeksi.SbmptnFragment
import com.rahmanarifofficial.mypik_pusatinformasikampus.view.trend.TrendFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: androidx.fragment.app.Fragment
    private lateinit var fragmentManager: androidx.fragment.app.FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init();
        bottom_navigation.inflateMenu(R.menu.menu_bottom_navigation);
        fragmentManager = supportFragmentManager;

        fragmentManager.beginTransaction().replace(
            R.id.main_container,
            KampusFragment()
        ).commit()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId
            when (id) {
                R.id.kampus_menu -> fragment =
                    KampusFragment()
                R.id.tren_menu -> fragment =
                    TrendFragment()
                R.id.beasiswa_menu -> fragment =
                    BeasiswaFragment()
                R.id.akun_menu -> fragment =
                    ProfileFragment()
            }
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, fragment)
            transaction.commit()
            true
        }
    }

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }
}
