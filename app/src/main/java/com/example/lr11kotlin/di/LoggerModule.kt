package com.example.lr11kotlin.di

import com.example.lr11kotlin.data.logger.AndroidLogger
import com.example.lr11kotlin.domain.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun bindLogger(impl: AndroidLogger): Logger
}