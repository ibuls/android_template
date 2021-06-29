package com

import android.app.Application
import okhttp3.Interceptor

class MyApplication : Application() {

    companion object{
        fun getNetworkInterceptor():Interceptor?{
        return null
        //   return FlipperManager.getFlipperInterceptor()
        }
    }
    override fun onCreate() {
        super.onCreate()
        /*if (BuildConfig.DEBUG) {
            FlipperManager.initNetworkDebug(this)
        }*/
    }


    override fun onTerminate() {
        super.onTerminate()
    }
}