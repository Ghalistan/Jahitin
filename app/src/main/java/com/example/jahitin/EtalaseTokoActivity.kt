package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Adapters.EtalaseAdapter
import com.example.jahitin.Models.BarangModel

class EtalaseTokoActivity : AppCompatActivity() {

    companion object {
        const val NAMA_TOKO = "nama_toko"
        const val DATA_BARANG = "data_barang"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etalase_toko)
        title = intent.getStringExtra(NAMA_TOKO)
        val data = intent.getParcelableArrayListExtra<BarangModel>(DATA_BARANG)

        val rvEtalase = findViewById<RecyclerView>(R.id.rv_etalase)
        rvEtalase.setHasFixedSize(true)
        rvEtalase.layoutManager = LinearLayoutManager(this)
        val adapter = EtalaseAdapter(data)
        rvEtalase.adapter = adapter
    }
}