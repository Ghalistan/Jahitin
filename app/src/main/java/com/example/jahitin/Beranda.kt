package com.example.jahitin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Adapters.BerandaAdapter
import com.example.jahitin.Models.TokoData
import com.example.jahitin.Models.TokoModel
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class Beranda : AppCompatActivity() {
    private var sampleImages = arrayOf(R.drawable.placeholder1, R.drawable.placeholder2, R.drawable.placeholder3)
    private lateinit var rvToko : RecyclerView
    private lateinit var progressBar: ProgressBar
    private var list : ArrayList<TokoModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val carousel = findViewById<CarouselView>(R.id.carousel_main)
        carousel.pageCount = sampleImages.size
        carousel.setImageListener(imageListener)

        progressBar = findViewById(R.id.main_progress_bar)
        progressBar.visibility = View.INVISIBLE

        rvToko = findViewById(R.id.rv_main_toko)
        rvToko.setHasFixedSize(true)

        list.addAll(TokoData.listData)
        rvToko.layoutManager = GridLayoutManager(this, 2)
        val berandaAdapter = BerandaAdapter(list)
        rvToko.adapter = berandaAdapter
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            Picasso.get()
                .load(sampleImages[position])
                .resize(1280,720)
                .into(imageView)
        }
    }
}