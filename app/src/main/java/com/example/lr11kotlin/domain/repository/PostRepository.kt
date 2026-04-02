package com.example.lr11kotlin.domain.repository

import androidx.paging.PagingData
import com.example.lr11kotlin.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<PagingData<Post>>

    suspend fun getPostsFromLocal(page: Int, limit: Int): List<Post>

    suspend fun getPostsFromRemote(page: Int, limit: Int): List<Post>

    suspend fun getPostsWithCacheStrategy(page: Int, limit: Int): List<Post>
}