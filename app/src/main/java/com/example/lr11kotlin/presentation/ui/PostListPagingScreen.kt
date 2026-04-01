package com.example.lr11kotlin.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lr11kotlin.domain.model.Post
import kotlinx.coroutines.flow.Flow

@Composable
fun PostListPagingScreen(
    postsFlow: Flow<PagingData<Post>>,
    modifier: Modifier = Modifier
) {

    val posts = postsFlow.collectAsLazyPagingItems()

    LazyColumn(

        modifier = modifier.fillMaxSize(),

        verticalArrangement = Arrangement.spacedBy(8.dp),

        contentPadding = PaddingValues(16.dp)

    ) {

        when (val refresh = posts.loadState.refresh) {

            is LoadState.Loading -> {

                item {

                    Box(

                        modifier = Modifier

                            .fillMaxWidth()

                            .height(200.dp),

                        contentAlignment = Alignment.Center

                    ) {

                        CircularProgressIndicator()

                    }

                }

            }

            is LoadState.Error -> {

                item {

                    ErrorItem(

                        message = refresh.error.message ?: "Ошибка загрузки",

                        onRetry = { posts.retry() }

                    )

                }

            }

            else -> {}

        }

        items(posts.itemCount) { index ->

            val post = posts[index]

            if (post != null) {

                PostItem(post)

            }

        }

        when (val append = posts.loadState.append) {

            is LoadState.Loading -> {

                item {

                    Box(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(16.dp),

                        contentAlignment = Alignment.Center

                    ) {

                        CircularProgressIndicator()

                    }

                }

            }

            is LoadState.Error -> {

                item {

                    ErrorItem(

                        message = append.error.message ?: "Ошибка",

                        onRetry = { posts.retry() }

                    )

                }

            }

            else -> {}

        }

    }

}

@Composable
private fun PostItem(post: Post) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor = MaterialTheme.colorScheme.surfaceVariant

        )

    ) {

        Column(Modifier.padding(16.dp)) {

            Text(

                text = post.title,

                fontWeight = FontWeight.Bold,

                style = MaterialTheme.typography.titleMedium

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = post.body,

                style = MaterialTheme.typography.bodySmall

            )

        }

    }

}

@Composable
private fun ErrorItem(

    message: String,

    onRetry: () -> Unit

) {

    Column(

        modifier = Modifier

            .fillMaxWidth()

            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(

            text = message,

            color = MaterialTheme.colorScheme.error

        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onRetry) {

            Text("Повторить")

        }

    }

}