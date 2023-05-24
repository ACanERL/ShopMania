package com.example.shopmania.data

import com.example.shopmania.data.model.ProductResponseModel
import com.example.shopmania.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    // get category names
    @GET(Constants.ALL_CATEGORIES)
    suspend fun getAllCategories(): List<String>

    @GET(Constants.SPECIFIC_CATEGORY)
    suspend fun getSpecificCategory(
        @Path("category") category:String
    ):List<ProductResponseModel>
}