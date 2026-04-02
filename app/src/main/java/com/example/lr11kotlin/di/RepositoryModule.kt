package com.example.lr11kotlin.di

import com.example.lr11kotlin.data.datasource.LocalPostDataSource
import com.example.lr11kotlin.data.datasource.RemotePostDataSource
import com.example.lr11kotlin.data.local.dao.PostDao
import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.data.remote.mapper.PostMapper
import com.example.lr11kotlin.data.repository.PostRepositoryImpl
import com.example.lr11kotlin.di.qualifier.LocalDataSource
import com.example.lr11kotlin.di.qualifier.RemoteDataSource
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
    @LocalDataSource
    fun provideLocalDataSource(postDao: PostDao): LocalPostDataSource {
        return LocalPostDataSource(postDao)
    }

    @Provides
    @Singleton
    @RemoteDataSource
    fun provideRemoteDataSource(
        postApi: PostApi,
        postMapper: PostMapper
    ): RemotePostDataSource {
        return RemotePostDataSource(postApi, postMapper)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        @LocalDataSource localDataSource: LocalPostDataSource,
        @RemoteDataSource remoteDataSource: RemotePostDataSource,
        postApi: PostApi,
        postMapper: PostMapper
    ): PostRepository {
        return PostRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            postApi = postApi,
            postMapper = postMapper
        )
    }
}