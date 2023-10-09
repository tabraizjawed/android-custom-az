package com.mvvm.aie.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBProduct::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}