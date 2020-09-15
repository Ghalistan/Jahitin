package com.example.jahitin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jahitin.Adapters.BerandaAdapter
import com.example.jahitin.Models.TokoData
import com.example.jahitin.Models.TokoModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import java.io.ByteArrayOutputStream
import kotlin.math.log

class BerandaActivity : AppCompatActivity() {
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

        val database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Pesan", snapshot.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        // Data Entry Firebase
//        val storage = Firebase.storage.reference
//        for (x in list) {
//            val key = database.child("Toko").push().key
//            database.child("Toko").child(key!!).setValue(x)
//            val imgBitmap = BitmapFactory.decodeResource(applicationContext.resources, x.fotoToko)
//            val baos = ByteArrayOutputStream()
//            imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//            val data = baos.toByteArray()
//
//            var pictref = storage.child("Toko/$key")
//            var upload = pictref.putBytes(data)
//            upload.continueWithTask { task ->
//                if (!task.isSuccessful) {
//                    task.exception?.let {
//                        throw it
//                    }
//                }
//                pictref.downloadUrl
//            }.addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val downloadUri = task.result
//                    database.child("Toko").child(key).child("fotoToko").setValue(downloadUri.toString())
//                } else {
//                    Log.w("Pesan", task.exception.toString())
//                }
//            }
//        }
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            Glide.with(applicationContext)
                .load(sampleImages[position])
                .apply(RequestOptions().override(1280,720))
                .into(imageView)
        }
    }
}