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
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.synnapps.carouselview.CarouselView

class BerandaActivity : AppCompatActivity() {
    private lateinit var rvToko : RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val database = Firebase.database.reference
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

        progressBar = findViewById(R.id.main_progress_bar)

        rvToko = findViewById(R.id.rv_main_toko)
        rvToko.setHasFixedSize(true)

        val mutableList = mutableListOf<TokoModel>()
        rvToko.layoutManager = GridLayoutManager(this, 2)
        val dataRef = database.child("Toko")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
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

    fun setCarousel(pictData : MutableList<String>) {
        val carousel = findViewById<CarouselView>(R.id.carousel_main)
        carousel.setImageListener { position, imageView ->
            Glide.with(applicationContext)
                .load(pictData[position])
                .apply(RequestOptions().override(1280, 720))
                .into(imageView)
        }
        carousel.pageCount = pictData.size
    }
}