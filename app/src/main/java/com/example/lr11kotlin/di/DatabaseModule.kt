package com.example.lr11kotlin.di

import android.content.Context
import androidx.room.Room
import com.example.lr11kotlin.data.local.AppDatabase
import com.example.lr11kotlin.data.local.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "posts_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(database: AppDatabase): PostDao {
        return database.postDao()
    }
}