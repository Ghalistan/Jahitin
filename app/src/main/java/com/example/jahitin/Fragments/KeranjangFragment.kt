package com.example.jahitin.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jahitin.Adapters.KeranjangAdapter
import com.example.jahitin.KeranjangInterface
import com.example.jahitin.Models.BarangModel
import com.example.jahitin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class KeranjangFragment : Fragment(), KeranjangInterface {
    private lateinit var database : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private lateinit var rvItemKeranjang : RecyclerView
    private lateinit var cl_keranjangKosong : ConstraintLayout
    private lateinit var cl_keranjang : ConstraintLayout
    private lateinit var progressBar : ConstraintLayout
    private lateinit var totalHarga : TextView
    private lateinit var adapter : KeranjangAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keranjang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItemKeranjang = view.findViewById(R.id.itemKeranjang)
        cl_keranjangKosong = view.findViewById(R.id.keranjangKosong)
        cl_keranjang = view.findViewById(R.id.tampilanKeranjang)
        progressBar = view.findViewById(R.id.progressbar)
        totalHarga = view.findViewById(R.id.totalHarga)

        database = Firebase.database.reference
        auth = Firebase.auth
        var user = auth.currentUser?.uid

        database.child("Keranjang").child(user.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == null) {
                        cl_keranjangKosong.visibility = View.VISIBLE
                        cl_keranjang.visibility = View.GONE
                    } else {
                        cl_keranjangKosong.visibility = View.GONE
                        cl_keranjang.visibility = View.VISIBLE

                        val itemBarang = mutableListOf<BarangModel>()
                        for (x in snapshot.children) {
                            val data = x.getValue(BarangModel::class.java)
                            itemBarang.add(data!!)
                        }

                        rvItemKeranjang.setHasFixedSize(true)
                        rvItemKeranjang.layoutManager = LinearLayoutManager(activity)
                        adapter = KeranjangAdapter(itemBarang, this@KeranjangFragment)
                        rvItemKeranjang.adapter = adapter
                        totalHarga.text = adapter.totalHarga()
                    }
                    progressBar.visibility = View.GONE
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    override fun updateTotalHarga() {
        Log.d("Pesan", "Bruh")
    }
}