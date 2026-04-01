package com.example.lr11kotlin.data.remote.mapper

import com.example.lr11kotlin.data.remote.dto.PostDto
import com.example.lr11kotlin.domain.model.Post

fun PostDto.toDomain(): Post {

    return Post(

        userId = userId,
        id = id,
        title = title,
        body = body

    )

}