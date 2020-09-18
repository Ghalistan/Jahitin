package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Fragments.AkunFragment
import com.example.jahitin.Fragments.BerandaFragment
import com.example.jahitin.Fragments.KeranjangFragment
import com.example.jahitin.Fragments.TransaksiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference

class BerandaActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {
    private lateinit var bnMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        setSupportActionBar(findViewById(R.id.berandaToolbar))
        bnMenu = findViewById(R.id.bottomNavMenu)
        bnMenu.setOnNavigationItemSelectedListener(this)
        bnMenu.setOnNavigationItemReselectedListener(this)
        loadFragment(BerandaFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navBeranda -> {
                title = "Jahitin"
                loadFragment(BerandaFragment())
                true
            }
            R.id.navKeranjang -> {
                title = "Keranjang"
                loadFragment(KeranjangFragment())
                true
            }
            R.id.navTransaksi -> {
                title = "Transaksi"
                loadFragment(TransaksiFragment())
                true
            }
            R.id.navAkun -> {
                title = "Akun"
                loadFragment(AkunFragment())
                true
            }
            else -> false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onNavigationItemReselected(item: MenuItem) {

    }
}