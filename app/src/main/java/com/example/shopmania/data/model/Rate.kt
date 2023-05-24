package com.example.shopmania.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rate
    (
    var rate:Double,
    var count:Int
): Parcelable