package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.email

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.title = getString(R.string.tentang_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_contact_developer.setOnClickListener {
            email("rahmanarifofficial@gmail.com","Kontak Kampus Ku")
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
