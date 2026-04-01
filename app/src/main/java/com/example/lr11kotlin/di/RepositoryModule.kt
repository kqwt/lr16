package com.example.lr11kotlin.di

import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.data.remote.mapper.PostMapper
import com.example.lr11kotlin.data.repository.PostRepositoryImpl
import com.example.lr11kotlin.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(
        postApi: PostApi,
        postMapper: PostMapper
    ): PostRepository {
        return PostRepositoryImpl(
            postApi = postApi,
            postMapper = postMapper
        )
    }
}