package com.example.jahitin.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jahitin.Adapters.BerandaAdapter
import com.example.jahitin.Models.TokoModel
import com.example.jahitin.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.synnapps.carouselview.CarouselView

class BerandaFragment : Fragment() {
    private lateinit var database : DatabaseReference
    private lateinit var progressBar : ProgressBar
    private lateinit var rvToko : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database.reference
        progressBar = view.findViewById(R.id.main_progress_bar)
        rvToko = view.findViewById(R.id.rv_main_toko)
        populateCarousel()
        populateRecycler()
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
                rvToko.setHasFixedSize(true)
                rvToko.layoutManager = GridLayoutManager(this@BerandaFragment.context, 2)
                rvToko.adapter = berandaAdapter
                progressBar.visibility = View.INVISIBLE
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyMessage", error.toException())
            }
        })
    }

    private fun setCarousel(pictData : MutableList<String>) {
        val carousel = view!!.findViewById<CarouselView>(R.id.carousel_main)
        carousel.setImageListener { position, imageView ->
            Glide.with(this)
                .load(pictData[position])
                .apply(RequestOptions().override(1280, 720))
                .into(imageView)
        }
        carousel.pageCount = pictData.size
    }

    private fun populateCarousel() {
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
}