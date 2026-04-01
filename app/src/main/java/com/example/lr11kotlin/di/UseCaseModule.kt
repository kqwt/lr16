package com.example.lr11kotlin.di

import com.example.lr11kotlin.domain.repository.PostRepository
import com.example.lr11kotlin.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(
        postRepository: PostRepository
    ): GetPostsUseCase {
        return GetPostsUseCase(postRepository)
    }
}