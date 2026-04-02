package com.example.lr11kotlin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lr11kotlin.data.datasource.LocalPostDataSource
import com.example.lr11kotlin.data.datasource.RemotePostDataSource
import com.example.lr11kotlin.data.remote.PostPagingSource
import com.example.lr11kotlin.data.remote.api.PostApi
import com.example.lr11kotlin.data.remote.mapper.PostMapper
import com.example.lr11kotlin.domain.datasource.PostDataSource
import com.example.lr11kotlin.domain.model.Post
import com.example.lr11kotlin.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.lr11kotlin.di.qualifier.LocalDataSource
import com.example.lr11kotlin.di.qualifier.RemoteDataSource

@Singleton
class PostRepositoryImpl @Inject constructor(
    @LocalDataSource private val localDataSource: LocalPostDataSource,
    @RemoteDataSource private val remoteDataSource: RemotePostDataSource,
    private val postApi: PostApi,
    private val postMapper: PostMapper
) : PostRepository {

    override fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostPagingSource(postApi, postMapper)
            }
        ).flow
    }

    override suspend fun getPostsFromLocal(page: Int, limit: Int): List<Post> {
        return localDataSource.getPosts(page, limit)
    }

    override suspend fun getPostsFromRemote(page: Int, limit: Int): List<Post> {
        val posts = remoteDataSource.getPosts(page, limit)
        localDataSource.savePosts(posts)
        return posts
    }

    override suspend fun getPostsWithCacheStrategy(
        page: Int,
        limit: Int
    ): List<Post> {
        return try {
            val remotePosts = remoteDataSource.getPosts(page, limit)
            localDataSource.savePosts(remotePosts)
            remotePosts
        } catch (e: Exception) {
            localDataSource.getPosts(page, limit)
        }
    }
}