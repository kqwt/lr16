package com.example.lr11kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lr11kotlin.presentation.ui.PostListPagingScreen
import com.example.lr11kotlin.presentation.viewmodel.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.lr11kotlin.ui.theme.Lr11kotlinTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadPosts()

        setContent {
            Lr11kotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PostListPagingScreen(
                        viewModel = viewModel
                    )
                }
            }
        }

    }

}