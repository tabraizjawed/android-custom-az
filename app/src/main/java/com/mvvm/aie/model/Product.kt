package com.mvvm.aie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Product(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "title") val title: String?
)