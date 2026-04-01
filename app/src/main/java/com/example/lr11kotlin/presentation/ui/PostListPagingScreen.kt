package com.example.lr11kotlin.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lr11kotlin.domain.model.Post
import com.example.lr11kotlin.presentation.viewmodel.PostListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListPagingScreen(
    viewModel: PostListViewModel
) {
    val posts = viewModel.posts.collectAsLazyPagingItems()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Posts") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    count = posts.itemCount,
                    key = { index -> posts[index]?.id ?: index }
                ) { index ->
                    posts[index]?.let { post ->
                        PostItem(post = post)
                    }
                }
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            error?.let { errorMessage ->
                ErrorBanner(
                    message = errorMessage,
                    onDismiss = { viewModel.clearError() }
                )
            }
        }
    }
}

@Composable
private fun PostItem(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorBanner(
    message: String,
    onDismiss: () -> Unit
) {
    Snackbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        action = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    ) {
        Text(text = message)
    }
}