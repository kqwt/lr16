package com.example.lr11kotlin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lr11kotlin.data.local.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM posts ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getPosts(limit: Long, offset: Long): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}