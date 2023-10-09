package com.mvvm.aie

import android.app.Application
import com.mvvm.aie.api.ProductsAPI
import com.mvvm.aie.repository.ProductsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AIEApplication : Application() {

    lateinit var apiService: ProductsAPI
    lateinit var productsRepository: ProductsRepository

    override fun onCreate() {
        super.onCreate()
        var retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ProductsAPI::class.java)
        productsRepository = ProductsRepository(apiService)
    }
}