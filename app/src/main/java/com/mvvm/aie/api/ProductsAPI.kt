package com.mvvm.aie.api

import com.mvvm.aie.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {

    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>

}