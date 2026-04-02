package com.example.lr11kotlin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.lr11kotlin.di.qualifier.IoDispatcher
import com.example.lr11kotlin.domain.logger.Logger
import com.example.lr11kotlin.domain.model.Post
import com.example.lr11kotlin.domain.repository.PostRepository
import com.example.lr11kotlin.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val postRepository: PostRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val logger: Logger
) : ViewModel() {

    private val _posts = MutableStateFlow<PagingData<Post>>(PagingData.empty())
    val posts: StateFlow<PagingData<Post>> = _posts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _cachedPosts = MutableStateFlow<List<Post>>(emptyList())
    val cachedPosts: StateFlow<List<Post>> = _cachedPosts

    init {
        logger.i(TAG, "ViewModel created")
    }

    fun loadPosts() {
        logger.d(TAG, "Loading posts")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getPostsUseCase()
                    .cachedIn(viewModelScope)
                    .collect { pagingData ->
                        _posts.value = pagingData
                        _isLoading.value = false
                        logger.i(TAG, "Posts loaded successfully")
                    }
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
                logger.e(TAG, "Error loading posts", e)
            }
        }
    }

    fun loadPostsWithCacheStrategy() {
        viewModelScope.launch(ioDispatcher) {
            _isLoading.value = true
            try {
                val posts = postRepository.getPostsWithCacheStrategy(
                    page = 1,
                    limit = 20
                )
                _cachedPosts.value = posts
                _isLoading.value = false
                logger.i(TAG, "Posts loaded with cache strategy: ${posts.size}")
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
                logger.e(TAG, "Error loading posts with cache", e)
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    override fun onCleared() {
        super.onCleared()
        logger.d(TAG, "ViewModel cleared")
    }

    companion object {
        private const val TAG = "PostListViewModel"
    }
}