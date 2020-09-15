package com.example.jahitin.Models

import com.example.jahitin.R

object TokoData {
    private val tokoFoto = intArrayOf(R.drawable.toko_placeholder1, R.drawable.toko_placeholder2, R.drawable.toko_placeholder3)
    private val tokoNama = arrayOf("Toko Bang Udin", "Bordir Mang Oleh", "Jahit Bu Yanti")
    private val tokoRating = doubleArrayOf(4.5, 3.8, 1.3)

    val listData : ArrayList<TokoModel>
        get() {
            val list = arrayListOf<TokoModel>()
            for (position in tokoNama.indices) {
                val toko = TokoModel()
                toko.fotoToko = tokoFoto[position]
                toko.namaToko = tokoNama[position]
                toko.ratingToko = tokoRating[position]
                toko.barangToko = BarangData.listData

                list.add(toko)
            }
            return list
        }
}