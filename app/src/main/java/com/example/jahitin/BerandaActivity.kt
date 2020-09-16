package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jahitin.Adapters.BerandaAdapter
import com.example.jahitin.Models.TokoModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.synnapps.carouselview.CarouselView

class BerandaActivity : AppCompatActivity() {
    private lateinit var rvToko : RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        setSupportActionBar(findViewById(R.id.berandaToolbar))

        progressBar = findViewById(R.id.main_progress_bar)

        database = Firebase.database.reference
        fetchCarouselData()

        rvToko = findViewById(R.id.rv_main_toko)
        rvToko.setHasFixedSize(true)
        rvToko.layoutManager = GridLayoutManager(this, 2)
        populateRecycler()
    }

    private fun setCarousel(pictData : MutableList<String>) {
        val carousel = findViewById<CarouselView>(R.id.carousel_main)
        carousel.setImageListener { position, imageView ->
            Glide.with(applicationContext)
                .load(pictData[position])
                .apply(RequestOptions().override(1280, 720))
                .into(imageView)
        }
        carousel.pageCount = pictData.size
    }

    private fun fetchCarouselData() {
        val carouselRef = database.child("Carousel")
        carouselRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val link = mutableListOf<String>()
                link.clear()
                for (data in snapshot.children) {
                    val value = data.getValue(String::class.java)
                    link.add(value!!)
                }
                setCarousel(link)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyMessage", error.toException())
            }
        })
    }

    private fun populateRecycler() {
        val dataRef = database.child("Toko")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mutableList = mutableListOf<TokoModel>()
                mutableList.clear()
                for (toko in snapshot.children) {
                    val data = toko.getValue(TokoModel::class.java)
                    mutableList.add(data!!)
                }
                val berandaAdapter = BerandaAdapter(mutableList)
                rvToko.adapter = berandaAdapter
                progressBar.visibility = View.INVISIBLE
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyMessage", error.toException())
            }
        })
    }
}