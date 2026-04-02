package com.example.lr11kotlin.data.datasource

import com.example.lr11kotlin.data.local.dao.PostDao
import com.example.lr11kotlin.data.local.entity.PostEntity
import com.example.lr11kotlin.domain.datasource.PostDataSource
import com.example.lr11kotlin.domain.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalPostDataSource @Inject constructor(
    private val postDao: PostDao
) : PostDataSource {

    override suspend fun getPosts(page: Int, limit: Int): List<Post> {
        val offset = (page - 1) * limit
        return postDao.getPosts(limit = limit.toLong(), offset = offset.toLong())
            .map { it.toDomain() }
    }

    suspend fun savePosts(posts: List<Post>) {
        val entities = posts.map { it.toEntity() }
        postDao.insertAll(entities)
    }
}

private fun Post.toEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId
    )
}

private fun PostEntity.toDomain(): Post {
    return Post(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId
    )
}