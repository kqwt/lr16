package com.example.lr11kotlin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lr11kotlin.data.remote.PostPagingSource
import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.domain.model.Post
import com.example.lr11kotlin.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(

    private val api: PostApi

) : PostRepository {

    override fun getPosts(): Flow<PagingData<Post>> {

        return Pager(

            config = PagingConfig(

                pageSize = 20

            ),

            pagingSourceFactory = {

                PostPagingSource(api)

            }

        ).flow

    }

}