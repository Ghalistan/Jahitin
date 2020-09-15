package com.example.jahitin.Adapters

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jahitin.EtalaseTokoActivity
import com.example.jahitin.Models.TokoModel
import com.example.jahitin.R

class BerandaAdapter(private val listToko : List<TokoModel>) : RecyclerView.Adapter<BerandaAdapter.BerandaViewHolder>() {
    class BerandaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardToko : CardView = itemView.findViewById(R.id.toko_cardView)
        var imageFoto : ImageView = itemView.findViewById(R.id.image_toko)
        var tvNamaToko : TextView = itemView.findViewById(R.id.tv_nama_toko)
        var ratingBar : RatingBar = itemView.findViewById(R.id.ratingBar_toko)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BerandaViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_toko_rekomendasi, parent, false)
        return BerandaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BerandaViewHolder, position: Int) {
        val toko = listToko[position]

        Glide.with(holder.itemView.context)
            .load(toko.fotoToko)
            .into(holder.imageFoto)

        holder.tvNamaToko.text = toko.namaToko
        holder.ratingBar.rating = toko.ratingToko.toFloat()
        holder.cardToko.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EtalaseTokoActivity::class.java)
            intent.putExtra(EtalaseTokoActivity.NAMA_TOKO, toko.namaToko)
            intent.putParcelableArrayListExtra(EtalaseTokoActivity.DATA_BARANG,  ArrayList(toko.barangToko))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listToko.size
    }
}