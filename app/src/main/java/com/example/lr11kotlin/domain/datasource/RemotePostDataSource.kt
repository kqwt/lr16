package com.example.lr11kotlin.data.datasource

import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.data.remote.mapper.PostMapper
import com.example.lr11kotlin.domain.datasource.PostDataSource
import com.example.lr11kotlin.domain.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemotePostDataSource @Inject constructor(
    private val postApi: PostApi,
    private val postMapper: PostMapper
) : PostDataSource {

    override suspend fun getPosts(page: Int, limit: Int): List<Post> {
        return postApi.getPosts(page = page, limit = limit)
            .map { postMapper.toDomain(it) }
    }
}