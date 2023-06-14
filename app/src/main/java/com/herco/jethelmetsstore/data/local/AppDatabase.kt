package com.herco.jethelmetsstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        HelmetEntity::class,
        SizeEntity::class,
    ], version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun helmetDao(): HelmetDao
}