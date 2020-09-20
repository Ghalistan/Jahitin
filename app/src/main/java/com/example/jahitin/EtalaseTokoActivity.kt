package com.example.jahitin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Adapters.EtalaseAdapter
import com.example.jahitin.Models.BarangModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EtalaseTokoActivity : AppCompatActivity() {
    private lateinit var rvEtalase : RecyclerView
    private lateinit var database : DatabaseReference
    private lateinit var kodeToko : String

    companion object {
        const val KODE_TOKO = "kode_toko"
        const val NAMA_TOKO = "nama_toko"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etalase_toko)

        setSupportActionBar(findViewById(R.id.etalaseToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = intent.getStringExtra(NAMA_TOKO)

        database = Firebase.database.reference
        kodeToko = intent.getStringExtra(KODE_TOKO)!!
        populateBarang()
    }

    fun populateBarang() {
        val dataRef = database.child("Toko").child(kodeToko).child("barangToko")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val kumpulanBarang = mutableListOf<BarangModel>()
                for (barang in snapshot.children) {
                    val data = barang.getValue(BarangModel::class.java)
                    kumpulanBarang.add(data!!)
                }
                rvEtalase = findViewById(R.id.rv_etalase)
                rvEtalase.setHasFixedSize(true)
                rvEtalase.layoutManager = LinearLayoutManager(this@EtalaseTokoActivity)
                val adapter = EtalaseAdapter(kumpulanBarang)
                rvEtalase.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Pesan", error.toException())
                Toast.makeText(this@EtalaseTokoActivity, "Terjadi Kesalahan silahkan coba lagi.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.etalase, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.detilToko -> {
                val kode = intent.getStringExtra(KODE_TOKO)
                val intent = Intent(this, InformasiTokoActivity::class.java)
                intent.putExtra(InformasiTokoActivity.KODE_TOKO, kode)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}