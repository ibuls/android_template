package com.dev.core.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/r/{subreddit}/hot.json")
      fun getTop(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Call<JsonObject>


    @GET("/r/dev}/hot.json")
    suspend fun getData(
      @Body json:JsonObject
    ): Call<JsonObject>
}