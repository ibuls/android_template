package com.dev.core.repository

import com.MyApplication
import com.dev.core.models.Posts
import com.google.gson.JsonObject
import com.mvvmref.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PostsRepository(private val app: MyApplication) : BaseRepository(app) {

   suspend fun getRedditPosts(): Flow<ApiResponse<Posts>> {
        return flow<ApiResponse<Posts>> {
            val client = getApiClient()
            hitApi<Posts>(client.getTop("dev",20,"",""), Posts::class.java).collect {
                emit(it)
            }
        }
    }
}