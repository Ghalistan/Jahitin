package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jahitin.Models.TokoModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class InformasiTokoActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var fotoToko : ImageView
    private lateinit var alamatToko : TextView
    private lateinit var noTelp : TextView
    private lateinit var ratingTokoAngka : TextView
    private lateinit var ratingToko : RatingBar
    private lateinit var ulasanKosong : TextView
    private lateinit var rvUlasan : RecyclerView

    companion object {
        const val KODE_TOKO = "KODE_TOKO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi_toko)

        setSupportActionBar(findViewById(R.id.informasiTokoToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fotoToko = findViewById(R.id.fotoToko)
        alamatToko = findViewById(R.id.alamatToko)
        noTelp = findViewById(R.id.noTelpToko)
        ratingTokoAngka = findViewById(R.id.angkatStatistik)
        ratingToko = findViewById(R.id.ratingBar)
        ulasanKosong = findViewById(R.id.ulasanKosong)
        rvUlasan = findViewById(R.id.rvUlasan)
        rvUlasan.visibility = View.GONE
        ulasanKosong.visibility = View.VISIBLE

        database = Firebase.database.reference
        val dataRef = database.child("Toko").child(intent.getStringExtra(KODE_TOKO)!!)
        dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(TokoModel::class.java)
                title = "Info ${data!!.namaToko}"

                Glide.with(this@InformasiTokoActivity)
                    .load(data.fotoToko)
                    .into(fotoToko)

                alamatToko.text =
                    if (data.alamatToko.isEmpty()) "Tidak ada alamat." else data.alamatToko
                noTelp.text =
                    if (data.noTelp.isEmpty()) "Tidak ada nomor telepon." else data.noTelp
                ratingTokoAngka.text = data.ratingToko.toString()
                ratingToko.rating = data.ratingToko.toFloat()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Pesan", error.toException())
            }
        })
    }
}