package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Fragments.BerandaFragment
import com.example.jahitin.Fragments.KeranjangFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference

class BerandaActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var bnMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        setSupportActionBar(findViewById(R.id.berandaToolbar))
        bnMenu = findViewById(R.id.bottomNavMenu)
        bnMenu.setOnNavigationItemSelectedListener(this)
        loadFragment(BerandaFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navBeranda -> {
                loadFragment(BerandaFragment())
                true
            }
            R.id.navKeranjang -> {
                Toast.makeText(this, "Keranjang", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.navTransaksi -> {
                Toast.makeText(this, "Transaksi", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.navAkun -> {
                Toast.makeText(this, "Akun", Toast.LENGTH_SHORT).show()
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
}