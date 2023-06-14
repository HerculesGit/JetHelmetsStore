package com.herco.jethelmetsstore.di

import android.content.Context
import androidx.room.Room
import com.herco.jethelmetsstore.data.local.AppDatabase

object LocalDatabaseModule {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context = context.applicationContext,
                AppDatabase::class.java,
                "helmet_db"
            ).build()

            INSTANCE = instance

            instance
        }
    }
}