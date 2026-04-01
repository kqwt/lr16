package com.example.lr11kotlin.domain.usecase

import androidx.paging.PagingData
import com.example.lr11kotlin.domain.model.Post
import com.example.lr11kotlin.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(

    private val repository: PostRepository

) {

    operator fun invoke(): Flow<PagingData<Post>> {

        return repository.getPosts()

    }

}