package com.mvvm.aie.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM DBProduct")
    suspend fun getAll(): List<DBProduct>

    @Insert
    suspend fun insertAll(products: List<DBProduct>)

    @Delete
    suspend fun delete(product: DBProduct)
}