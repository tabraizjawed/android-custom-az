package com.mvvm.aie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.aie.db.DatabaseHelper
import com.mvvm.aie.repository.ProductsRepository

class ViewModeFactory(private val productsRepository: ProductsRepository, private val databaseHelperImpl: DatabaseHelper) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(productsRepository,databaseHelperImpl) as T
        }
        throw IllegalArgumentException("unknown request")
    }
}