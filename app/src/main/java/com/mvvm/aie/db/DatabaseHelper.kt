package com.mvvm.aie.db

class DatabaseHelper(private val appDatabase: AppDatabase) {

     suspend fun getProducts(): List<DBProduct> {
        return appDatabase.userDao().getAll()
    }

     suspend fun insertAll(products: List<DBProduct>) {
        appDatabase.userDao().insertAll(products)
    }

}