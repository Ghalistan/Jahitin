package com.example.jahitin

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jahitin.Fragments.AkunFragment
import com.example.jahitin.Fragments.BerandaFragment
import com.example.jahitin.Fragments.KeranjangFragment
import com.example.jahitin.Fragments.TransaksiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BerandaActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {
    private lateinit var bnMenu: BottomNavigationView
    private lateinit var imgIcon : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
        title = ""
        imgIcon = findViewById(R.id.logoIcon)

        setSupportActionBar(findViewById(R.id.berandaToolbar))
        bnMenu = findViewById(R.id.bottomNavMenu)
        bnMenu.setOnNavigationItemSelectedListener(this)
        bnMenu.setOnNavigationItemReselectedListener(this)
        loadFragment(BerandaFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navBeranda -> {
                supportActionBar?.show()
                title = ""
                imgIcon.visibility = View.VISIBLE
                loadFragment(BerandaFragment())
                true
            }
            R.id.navKeranjang -> {
                supportActionBar?.show()
                title = "Keranjang"
                imgIcon.visibility = View.GONE
                loadFragment(KeranjangFragment())
                true
            }
            R.id.navTransaksi -> {
                supportActionBar?.show()
                title = "Transaksi"
                imgIcon.visibility = View.GONE
                loadFragment(TransaksiFragment())
                true
            }
            R.id.navAkun -> {
                supportActionBar?.hide()
                imgIcon.visibility = View.GONE
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