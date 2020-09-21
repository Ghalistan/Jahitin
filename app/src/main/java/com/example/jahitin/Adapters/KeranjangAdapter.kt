package com.example.jahitin.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jahitin.Fragments.KeranjangFragment
import com.example.jahitin.KeranjangInterface
import com.example.jahitin.Models.BarangModel
import com.example.jahitin.R
import java.text.NumberFormat
import java.util.*

class KeranjangAdapter(val barang : MutableList<BarangModel>, val keranjang : KeranjangInterface) :
    RecyclerView.Adapter<KeranjangAdapter.KeranjangViewHolder>() {

    class KeranjangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fotoBaju : ImageView = itemView.findViewById(R.id.fotoBaju)
        var namaBaju : TextView = itemView.findViewById(R.id.namaBaju)
        var hargaBaju : TextView = itemView.findViewById(R.id.hargaBaju)
        var buangBaju : ImageView = itemView.findViewById(R.id.buangBaju)
        var tambahBaju : ImageView = itemView.findViewById(R.id.tambahBaju)
        var kurangiBaju : ImageView = itemView.findViewById(R.id.kurangiBaju)
        var jumlahBaju : TextView = itemView.findViewById(R.id.jumlahBaju)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
        return KeranjangViewHolder(view)
    }

    override fun onBindViewHolder(holder: KeranjangViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(barang[position].foto)
            .into(holder.fotoBaju)

        holder.namaBaju.text = barang[position].nama
        holder.hargaBaju.text = getCurrencies(barang[position].Harga)
        holder.buangBaju.setOnClickListener {

        }
        holder.tambahBaju.setOnClickListener {
            val amount = holder.jumlahBaju.text.toString().toInt()
            holder.jumlahBaju.text = (amount + 1).toString()
            keranjang.updateTotalHarga()
        }
        holder.kurangiBaju.setOnClickListener {
            val amount = holder.jumlahBaju.text.toString().toInt()
            if (amount > 1) {
                holder.jumlahBaju.text = (amount - 1).toString()
                keranjang.updateTotalHarga()
            }
        }
    }

    fun totalHarga() : String {
        val list = barang.map { it.Harga }

        return getCurrencies(list.sum())
    }

    override fun getItemCount(): Int = barang.size

    private fun getCurrencies(int: Int) : String {
        val number = NumberFormat.getCurrencyInstance(Locale("ID"))
        number.maximumFractionDigits = 0
        number.currency = Currency.getInstance("IDR")
        return number.format(int)
    }
}