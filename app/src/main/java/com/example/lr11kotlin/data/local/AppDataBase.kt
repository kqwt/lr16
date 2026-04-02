package com.example.lr11kotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lr11kotlin.data.local.dao.PostDao
import com.example.lr11kotlin.data.local.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}