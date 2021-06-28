package com

import android.app.Application
import com.dev.BuildConfig
import okhttp3.Interceptor

class MyApplication:Application() {
    companion object {
        // Flipper Integration
        private var networkFlipperPlugin: com.facebook.flipper.plugins.network.NetworkFlipperPlugin? = null

        private fun getFlipperNetworkClient(): com.facebook.flipper.plugins.network.NetworkFlipperPlugin {
            if (networkFlipperPlugin == null) {
                networkFlipperPlugin = com.facebook.flipper.plugins.network.NetworkFlipperPlugin()
            }
            return networkFlipperPlugin!!
        }

        @JvmStatic
        fun getFlipperInterceptor(): Interceptor? {
            //return null
            return com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor(getFlipperNetworkClient())
        }

    }

    fun initNetworkDebug() {
        // Flipper
        if (BuildConfig.DEBUG && com.facebook.flipper.android.utils.FlipperUtils.shouldEnableFlipper(this)) {
            com.facebook.soloader.SoLoader.init(this, false);
            val client = com.facebook.flipper.android.AndroidFlipperClient.getInstance(this)
            client.addPlugin(getFlipperNetworkClient())
            client.addPlugin(com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin(this, com.facebook.flipper.plugins.inspector.DescriptorMapping.withDefaults()))
            client.start()
        }
    }
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initNetworkDebug()
        }

    }


    override fun onTerminate() {
        super.onTerminate()

    }
}