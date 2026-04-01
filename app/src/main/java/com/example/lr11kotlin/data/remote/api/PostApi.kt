package com.example.lr11kotlin.data.remote.api

import com.example.lr11kotlin.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getPosts(

        @Query("_page")
        page: Int,

        @Query("_limit")
        limit: Int

    ): List<PostDto>

}