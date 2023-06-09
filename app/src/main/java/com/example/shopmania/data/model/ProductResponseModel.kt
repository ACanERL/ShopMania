package com.example.shopmania.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class ProductResponseModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double?,
    val title: String,
    var count: Int,
    var isFavorite: Boolean = false,
    var rating: Rate? = null,
):Parcelable