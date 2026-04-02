package com.example.lr11kotlin.data.remote.mapper

import com.example.lr11kotlin.data.remote.dto.PostDto
import com.example.lr11kotlin.domain.model.Post

class PostMapper {
    fun toDomain(dto: PostDto): Post {
        return Post(
            id = dto.id,
            title = dto.title,
            body = dto.body,
            userId = dto.userId
        )
    }
}