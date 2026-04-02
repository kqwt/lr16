package com.example.lr11kotlin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val body: String,
    val userId: Long
)