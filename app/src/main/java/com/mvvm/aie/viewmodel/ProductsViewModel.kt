package com.mvvm.aie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.aie.db.DBProduct
import com.mvvm.aie.db.DatabaseHelper
import com.mvvm.aie.model.Product
import com.mvvm.aie.repository.ProductsRepository
import com.mvvm.aie.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class ProductsViewModel(
    private val productsRepository: ProductsRepository,
    private val databaseHelper: DatabaseHelper
) : ViewModel() {

    private val _products = MutableLiveData<NetworkResult<List<Product>>>()

    val products: LiveData<NetworkResult<List<Product>>>
        get() = _products


    fun getProducts() {
        _products.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                val result = productsRepository.getProducts()
                if (result.data?.isNotEmpty() == true) {
                    _products.postValue(result)
                }
                val listToAdd = mutableListOf<DBProduct>()
                result.data?.forEach {
                    var dbProduct = DBProduct(
                        it.id,
                        it.price,
                        it.title,
                        it.image
                    )
                    listToAdd.add(dbProduct)
                }
                databaseHelper.insertAll(listToAdd)

            } catch (ex: Exception) {
                if (ex.message!!.contains("Unable to resolve host")) {
                    val returnList = mutableListOf<Product>()
                    databaseHelper.getProducts().map {
                        var product = Product(
                            id = it.id,
                            price = it.price,
                            title = it.title,
                            image = it.image,
                            category = null,
                            description = null
                        )
                        returnList.add(product)
                    }
                    _products.postValue(NetworkResult.Success(returnList))
                } else
                    _products.postValue(NetworkResult.Error(message = ex.message.toString()))
            }
        }
    }
}