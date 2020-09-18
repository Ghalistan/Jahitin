package com.example.jahitin.Models

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties
data class BarangModel(
    var foto : String = "",
    var nama : String = "",
    var bahan : String = "",
    var Ukuran : String = "",
    var Harga : Int = 0,
) : Parcelable