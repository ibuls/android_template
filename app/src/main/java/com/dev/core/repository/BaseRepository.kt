package com.dev.core.repository

import com.MyApplication
import com.dev.BuildConfig
import com.dev.R
import com.dev.core.api.ApiCallback
import com.dev.core.api.ApiInterface
import com.dev.core.api.ErrorCode
import com.dev.utils.ConnectionDetector
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.hummguide.api.ApiService
import com.mvvmref.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

open class BaseRepository(private val app: MyApplication) {

    fun getApiClient(): ApiInterface {
        return ApiService.getClient(app, if (BuildConfig.DEBUG)MyApplication.getNetworkInterceptor() else null)
    }

    suspend fun <T> hitApi(call: Call<JsonObject>?, objectType: Class<T>): Flow<ApiResponse<T>> {
     return flow {

         if (!ConnectionDetector(app).isConnectedToInternet) {
             emit(ApiResponse.error<T>(
                 null,
                 app.getString(R.string.no_internet_access),
                 ErrorCode.NO_INTERNET_ACCESS
             ))
             return@flow
         }

         emit(ApiResponse.loading())
         try {
             val response = call?.execute()

             if (response != null) {
                 val status = getResponseStatus(response)
                 if (status.first == 200) {
                      try {
                         emit(ApiResponse.success(
                             GsonBuilder().create().fromJson(response.body(), objectType)
                         ))
                     } catch (e: Exception) {
                         e.printStackTrace()
                         emit(ApiResponse.error<T>(
                             null,
                             app.getString(R.string.unknown_error),
                             ErrorCode.GSON_PARCE_ERROR
                         ))
                     }
                 } else {
                     emit(ApiResponse.error<T>(
                         null,
                         status.second,
                         status.first
                     ))
                 }
             } else {
                 emit(ApiResponse.error<T>(
                     null,
                     app.getString(R.string.unknown_error),
                     ErrorCode.UNKNOWN_SERVER_ERROR
                 ))
             }

         } catch (e: Exception) {
             emit(ApiResponse.error<T>(
                 null,
                 app.getString(R.string.unknown_error),
                 ErrorCode.UNKNOWN_SERVER_ERROR
             ))
         }
     }

    }


    // logics here may change from app to app
    fun getResponseStatus(response: Response<JsonObject>): Pair<Int,String> {
        var result: Boolean
        var code = 200
        var message = ""
        if (response.isSuccessful) {

            code = 200
            message = ""
        /*
        // this is app specific logic
        try {
                when (response.body()?.get("status")?.asInt) {
                    200 -> {
                        code = 200
                        message = ""
                    }
                    ErrorCode.UNAUTHORIZED_ACCESS -> {
                        code = ErrorCode.UNAUTHORIZED_ACCESS
                        message =response.body()?.get("message")?.asString?:app.getString(R.string.unknown_error)
                    }
                    else -> {
                        code = ErrorCode.UNKNOWN_SERVER_ERROR
                        message =response.body()?.get("message")?.asString?:app.getString(R.string.unknown_error)
                    }
                }
            } catch (e: Exception) {
                message =app.getString(R.string.unknown_error)
                code = ErrorCode.UNKNOWN_SERVER_ERROR
            }*/
        } else {
            message =app.getString(R.string.unknown_error)
            code = ErrorCode.UNKNOWN_SERVER_ERROR
        }


        return Pair(code,message)
    }
}