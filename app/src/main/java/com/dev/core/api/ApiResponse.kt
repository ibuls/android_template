package com.mvvmref.utils

data class ApiResponse<T>(val status: ApiStatus, val data:T?, val message:String?) {
    companion object {

        fun <T> success(data:T):ApiResponse<T> = ApiResponse(ApiStatus.SUCCESS,data,null)

        fun <T> error(data:T? = null,message: String,code:Int) = ApiResponse(ApiStatus.ERROR,data,message)

        fun <T> loading(data:T? = null) = ApiResponse(ApiStatus.LOADING,data,null)
    }
}