package com.example.jahitin.Models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TokoModel(
    var fotoToko : String = "",
    var namaToko : String = "",
    var ratingToko : Double = 0.0,
    var barangToko : List<BarangModel> = emptyList()
)