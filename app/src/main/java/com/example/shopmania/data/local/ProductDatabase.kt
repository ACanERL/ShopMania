package com.example.shopmania.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopmania.data.model.CartModel
import com.example.shopmania.data.model.FavoriteModel

@Database(entities = [CartModel::class, FavoriteModel::class], version = 4,exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun getProductFromDao():ProductDao
}