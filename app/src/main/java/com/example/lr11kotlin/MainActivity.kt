package com.example.lr11kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.lr11kotlin.presentation.ui.PostListPagingScreen
import com.example.lr11kotlin.ui.theme.Lr11kotlinTheme

class MainActivity : ComponentActivity() {

    private val dependencies by lazy {

        PostDependencies()

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            Lr11kotlinTheme {

                Scaffold(

                    modifier = Modifier.fillMaxSize()

                ) { padding ->

                    PostListPagingScreen(

                        postsFlow = dependencies.getPostsUseCase(),

                        modifier = Modifier.padding(padding)

                    )

                }

            }

        }

    }

}