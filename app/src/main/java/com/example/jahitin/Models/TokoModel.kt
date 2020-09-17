package com.example.jahitin.Models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TokoModel(
    var kodeToko : String = "",
    var fotoToko : String = "",
    var namaToko : String = "",
    var ratingToko : Double = 0.0,
    var alamatToko : String = "",
    var noTelp : String = "",
    var barangToko : List<BarangModel> = emptyList()
)