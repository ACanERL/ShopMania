package com.example.shopmania.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopmania.data.model.CartModel
import com.example.shopmania.data.model.FavoriteModel

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(product: CartModel)

    @Query("SELECT * FROM cartTable")
    fun getProductToCart():List<CartModel>

    @Query("DELETE FROM cartTable WHERE id=:id")
    suspend fun deleteProductFromCart(id:Int)

    @Query("DELETE FROM cartTable")
    suspend fun clearCart()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToFavorite(product: FavoriteModel)

    @Query("DELETE FROM favoriteTable WHERE id=:favId")
    suspend fun deleteProductFromFavorite(favId:Int)

    @Query("SELECT * FROM favoriteTable")
    fun getProductToFavorite():List<FavoriteModel>

    @Query("SELECT title FROM favoriteTable")
    fun getFavoritesTitles():List<String>?
}