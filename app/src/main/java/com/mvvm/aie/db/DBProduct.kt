package com.mvvm.aie.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBProduct(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "image") val image: String?
)