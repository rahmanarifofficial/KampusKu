package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_bantuan.*
import org.jetbrains.anko.email

class BantuanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)
        supportActionBar?.title = getString(R.string.bantuan_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_send_question.setOnClickListener {
            email(
                "rahmanarifofficial@gmail.com", "Pertanyaan Bantuan Kampus Ku",
                et_content_pertanyaan.text.toString()
            )
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
