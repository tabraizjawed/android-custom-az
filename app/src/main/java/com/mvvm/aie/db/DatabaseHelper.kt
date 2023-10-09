package com.mvvm.aie.db

class DatabaseHelper(private val appDatabase: AppDatabase) {

     suspend fun getProducts(): List<DBProduct> {
        return appDatabase.productDao().getAll()
    }

     suspend fun insertAll(products: List<DBProduct>) {
        appDatabase.productDao().insertAll(products)
    }

}