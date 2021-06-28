package com.dev.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectionDetector(private val _context: Context) {
    /**
     * Checking for all possible internet providers
     */
    val isConnectedToInternet: Boolean
        get() = isConnectedWiFi || isConnectedMobileNetwork
    val isConnectedWiFi: Boolean
        get() {
            val connectivityManager =
                _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnected
        }
    val isConnectedMobileNetwork: Boolean
        get() {
            var kResult = false
            try {
                val connectivityManager =
                    _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                kResult =
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnected
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return kResult
        }
}