package com.mvvm.aie.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDb(context)
                }
            }
        }
        return INSTANCE!!
    }


    private fun buildDb(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "database-pro"
    ).build()

}