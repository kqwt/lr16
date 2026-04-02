package com.example.lr11kotlin.domain.datasource

import com.example.lr11kotlin.domain.model.Post

interface PostDataSource {
    suspend fun getPosts(page: Int, limit: Int): List<Post>
}