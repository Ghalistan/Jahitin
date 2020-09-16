package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Adapters.EtalaseAdapter
import com.example.jahitin.Models.BarangModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EtalaseTokoActivity : AppCompatActivity() {
    private lateinit var rvEtalase : RecyclerView

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

        val database = Firebase.database.reference
        val dataRef = database.child("Toko").child(intent.getStringExtra(KODE_TOKO)!!).child("barangToko")
        dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
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
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.etalase, menu)
        return true
    }
}