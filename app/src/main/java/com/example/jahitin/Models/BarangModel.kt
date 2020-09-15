package com.example.jahitin.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BarangModel(
    var foto : Int = 0,
    var nama : String = "",
    var bahan : String = "",
    var Ukuran : String = "",
    var Harga : Int = 0,
) : Parcelable