package com.example.lr11kotlin.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.data.remote.mapper.toDomain
import com.example.lr11kotlin.domain.model.Post

class PostPagingSource(

    private val api: PostApi

) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {

        return try {

            val page = params.key ?: 1

            val response = api.getPosts(

                page = page,
                limit = params.loadSize

            )

            LoadResult.Page(

                data = response.map { it.toDomain() },

                prevKey = if (page == 1) null else page - 1,

                nextKey = if (response.isEmpty()) null else page + 1

            )

        } catch (e: Exception) {

            LoadResult.Error(e)

        }

    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {

        return state.anchorPosition

    }

}