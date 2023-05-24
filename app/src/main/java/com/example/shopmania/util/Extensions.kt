package com.example.shopmania.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.shopmania.R
import java.text.DecimalFormat

fun Context.toastMessage(message:String) = Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
fun View.click(function: ()->Unit){
    this.setOnClickListener {
        function()
    }
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

private val formatter2= DecimalFormat("##.##")
private val formatter3= DecimalFormat("##.###")

fun Double.roundToTwoDecimals() = formatter2.format(this).toString()
fun Double.roundToThreeDecimals() = formatter3.format(this).toString()

fun ImageView.downloadImage(imageUrl:String){
    Glide.with(context).load(imageUrl).into(this)
}
@BindingAdapter("android:downloadImage")
fun bindImage(imageView: ImageView, imageUrl:String){
    imageView.downloadImage(imageUrl)
}

@BindingAdapter("favState")
fun favState(imageView: ImageView, isFavorite:Boolean){
    if (isFavorite) imageView.setImageResource(R.drawable.fav_icon_fill)
    else imageView.setImageResource(R.drawable.baseline_favorite_border_24)
}