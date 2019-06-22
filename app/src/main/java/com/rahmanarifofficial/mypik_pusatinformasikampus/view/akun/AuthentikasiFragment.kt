package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.AkunPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import org.jetbrains.anko.support.v4.toast


class AuthentikasiFragment : Fragment(), View.OnClickListener, AkunView {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    private lateinit var prefs: AuthPreferences

    private lateinit var btnSignIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var fieldEmail: EditText
    private lateinit var fieldPassword: EditText
    private lateinit var pbAuth: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.text_auth))
        val v = inflater.inflate(R.layout.fragment_authentikasi, container, false)
        fieldEmail = v.findViewById(R.id.et_email)
        fieldPassword = v.findViewById(R.id.et_password)
        pbAuth = v.findViewById(R.id.pb_auth)
        btnSignIn = v.findViewById(R.id.btn_sign_in)
        btnSignUp = v.findViewById(R.id.btn_sign_up)
        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        prefs = AuthPreferences(activity!!)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_in -> loginPengguna()
            R.id.btn_sign_up -> daftarPengguna()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.getCurrentUser()
        updateUI(currentUser, 1)
    }

    private fun loginPengguna() {
        fieldEmail.setError(null)
        fieldPassword.setError(null)

        // Store values at the time of the login attempt.
        val email = fieldEmail.getText().toString()
        val password = fieldPassword.getText().toString()

        var cancel = false

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            fieldEmail.setError(getString(R.string.error_field_required))
            cancel = true
        }

        // Check for a valid email password.
        if (TextUtils.isEmpty(password)) {
            fieldPassword.setError(getString(R.string.error_field_required))
            cancel = true
        }

        if (!cancel) {
            prefs.setSaveEmail(email)
            prefs.setSavePassword(password)
            AkunPresenter.loginPengguna(this, email, password, activity!!)
        }

    }

    private fun daftarPengguna() {
        // Store values at the time of the login attempt.
        val email = fieldEmail.getText().toString()
        val password = fieldPassword.getText().toString()

        var cancel = false

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            fieldEmail.setError(getString(R.string.error_field_required))
            cancel = true
        }

        // Check for a valid email password.
        if (TextUtils.isEmpty(password)) {
            fieldPassword.setError(getString(R.string.error_field_required))
            cancel = true
        }
        if (!cancel) {
            prefs.setSaveEmail(email)
            prefs.setSavePassword(password)
            AkunPresenter.daftarPengguna(this, email, password, activity!!)
        }
    }

    override fun showToast(data: String) {
        toast(data)
    }

    override fun updateUI(user: FirebaseUser?, type: Int) {
        if (user != null) {
            when (type) {
                1 -> showFragment(ProfileFragment())
                2 -> showFragment(NewProfileFragment())
            }
        }
    }

    override fun showProgress() {
        pbAuth.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbAuth.visibility = View.INVISIBLE
    }

    fun showFragment(fragment: Fragment) {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        ft.replace(R.id.main_container, fragment)
        ft.commit()
    }
}
