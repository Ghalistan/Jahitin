package com.example.jahitin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jahitin.Models.BarangModel
import com.example.jahitin.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class EtalaseAdapter(private val barang: MutableList<BarangModel>) : RecyclerView.Adapter<EtalaseAdapter.EtalaseViewHolder>() {
    class EtalaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foto : ImageView = itemView.findViewById(R.id.foto_baju)
        var nama : TextView = itemView.findViewById(R.id.nama_baju)
        var bahan : TextView = itemView.findViewById(R.id.bahan_baju)
        var ukuran : TextView = itemView.findViewById(R.id.ukuran_baju)
        var harga : TextView = itemView.findViewById(R.id.harga_baju)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtalaseViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_etalase_toko, parent, false)
        return EtalaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: EtalaseViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(barang[position].foto)
            .into(holder.foto)

        holder.nama.text = barang[position].nama
        val bahan = "Bahan : " + barang[position].bahan
        holder.bahan.text = bahan
        val ukuran = "Ukuran : " + barang[position].Ukuran
        holder.ukuran.text = ukuran
        holder.harga.text = getCurrencies(barang[position].Harga)
    }

    override fun getItemCount(): Int = barang.size

    private fun getCurrencies(int: Int) : String {
        val number = NumberFormat.getCurrencyInstance(Locale("ID"))
        number.maximumFractionDigits = 0
        number.currency = Currency.getInstance("IDR")
        return number.format(int)
    }
}