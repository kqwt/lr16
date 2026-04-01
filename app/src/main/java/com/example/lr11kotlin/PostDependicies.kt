package com.example.lr11kotlin

import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.data.repository.PostRepositoryImpl
import com.example.lr11kotlin.domain.repository.PostRepository
import com.example.lr11kotlin.domain.usecase.GetPostsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDependencies {

    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()

            .baseUrl("https://jsonplaceholder.typicode.com/")

            .addConverterFactory(GsonConverterFactory.create())

            .build()

    }

    private val api: PostApi by lazy {

        retrofit.create(PostApi::class.java)

    }

    private val repository: PostRepository by lazy {

        PostRepositoryImpl(api)

    }

    val getPostsUseCase by lazy {

        GetPostsUseCase(repository)

    }

}