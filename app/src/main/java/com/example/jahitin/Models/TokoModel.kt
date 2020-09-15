package com.example.jahitin.Models

data class TokoModel(
    var fotoToko : Int = 0,
    var namaToko : String = "",
    var ratingToko : Double = 0.0,
    var barangToko : List<BarangModel> = emptyList()
)