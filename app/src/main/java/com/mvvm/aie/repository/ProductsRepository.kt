package com.mvvm.aie.repository

import android.text.TextUtils
import com.mvvm.aie.api.ProductsAPI
import com.mvvm.aie.model.Product
import com.mvvm.aie.utils.NetworkResult

class ProductsRepository(private var apiService: ProductsAPI) {

    suspend fun getProducts(): NetworkResult<List<Product>> {

        val response = apiService.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error(message = "erorr")
            }

        } else {
            NetworkResult.Error(message = "error loading data")
        }
    }
}