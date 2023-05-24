package com.example.shopmania.di

import android.content.Context
import androidx.room.Room
import com.example.shopmania.data.Api
import com.example.shopmania.data.local.ProductDao
import com.example.shopmania.data.local.ProductDatabase
import com.example.shopmania.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):Api{
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context): ProductDatabase =
        Room.databaseBuilder(context,ProductDatabase::class.java,"productDatabase")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(database:ProductDatabase): ProductDao {
        return database.getProductFromDao()
    }

}