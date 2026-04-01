package com.example.lr11kotlin.data.remote.mapper

import com.example.lr11kotlin.data.remote.dto.PostDto
import com.example.lr11kotlin.domain.model.Post

class PostMapper {
    fun PostDto.toDomain(): Post {
        return Post(
            id = this.id,
            title = this.title,
            body = this.body,
            userId = this.userId
        )
    }
}