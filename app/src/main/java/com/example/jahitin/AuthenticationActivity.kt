package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jahitin.Fragments.LoginFragment
import com.example.jahitin.Fragments.RegisterFragment

class AuthenticationActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var register : TextView
    private lateinit var login : TextView
    private lateinit var authText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        register = findViewById(R.id.register)
        register.setOnClickListener(this)
        login = findViewById(R.id.login)
        login.setOnClickListener(this)
        authText = findViewById(R.id.akunStatus)

        loadFragment(LoginFragment())
    }

    private fun loadFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authLayout, fragment)
            .commit()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.register -> {
                register.visibility = View.GONE
                login.visibility = View.VISIBLE
                authText.text = "Sudah memiliki akun ?"
                loadFragment(RegisterFragment())
            }
            R.id.login -> {
                login.visibility = View.GONE
                register.visibility = View.VISIBLE
                authText.text = "Belum memiliki akun ?"
                loadFragment(LoginFragment())
            }
        }
    }
}