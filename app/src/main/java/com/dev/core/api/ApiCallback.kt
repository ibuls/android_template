package com.dev.core.api

import com.mvvmref.utils.ApiResponse

fun interface ApiCallback<T> {
    fun onApiResponse(response:ApiResponse<T>)
}