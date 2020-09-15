package com.example.jahitin.Models

import com.example.jahitin.R
import kotlinx.android.synthetic.main.item_etalase_toko.view.*

object BarangData {
    val foto = intArrayOf(R.drawable.baju_placeholder1, R.drawable.baju_placeholder2, R.drawable.baju_placeholder3)
    val nama = arrayOf("Jaket Tebal Afrika", "Double layer T-Shirt", "Baju Pantai")
    val bahan = arrayOf("Katun", "Kain Baja", "Tisu")
    val ukuran = arrayOf("M dan XL", "All Size", "X dan XL")
    val harga = arrayOf(120000, 80000, 40000)

    val listData : List<BarangModel>
        get() {
            val list = arrayListOf<BarangModel>()
            for (position in nama.indices) {
                val barangModel = BarangModel()
                barangModel.foto = foto[position]
                barangModel.nama = nama[position]
                barangModel.bahan = bahan[position]
                barangModel.Ukuran = ukuran[position]
                barangModel.Harga = harga[position]

                list.add(barangModel)
            }
            return list
        }
}