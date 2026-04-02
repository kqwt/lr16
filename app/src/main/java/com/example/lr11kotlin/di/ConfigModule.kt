package com.example.lr11kotlin.di

import com.example.lr11kotlin.di.qualifier.BaseUrl
import com.example.lr11kotlin.di.qualifier.DefaultDispatcher
import com.example.lr11kotlin.di.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Singleton
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://jsonplaceholder.typicode.com/"
    }
}