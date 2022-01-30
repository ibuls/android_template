package com.hummguide.api

import android.content.Context
import android.provider.Settings
import com.dev.BuildConfig
import com.dev.core.api.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ApiService {


    companion object {

        private var retrofit: Retrofit? = null
        fun getClient(context: Context,networkInterceptor: Interceptor?): ApiInterface {

            val mCalendar: Calendar = GregorianCalendar()
            val mTimeZone = mCalendar.timeZone
            val mGMTOffset = mTimeZone.rawOffset
            val timezoneOffset = TimeUnit.MILLISECONDS.convert(
                mGMTOffset.toLong(),
                TimeUnit.MILLISECONDS
            )
            if (retrofit == null) {
                val okHttpClientBuilder = OkHttpClient().newBuilder()
                    .connectTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .writeTimeout(40, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val request = original.newBuilder()
                            .header("platform", "Android")
                            .header("osVersion", android.os.Build.VERSION.SDK_INT.toString())
                            .header(
                                "device_id",
                                Settings.Secure.getString(
                                    context.getContentResolver(),
                                    Settings.Secure.ANDROID_ID
                                ).toString()
                            )
                            .header("appVersion", BuildConfig.VERSION_CODE.toString())
                            .header("time", System.currentTimeMillis().toString())
                            .header("timeZoneOffset", timezoneOffset.toString())
                            .method(original.method, original.body)
                            .build()
                        chain.proceed(request)
                    }


                if (BuildConfig.DEBUG){
                    networkInterceptor?.let {
                        okHttpClientBuilder.addNetworkInterceptor(it)
                    }
                }

                val okHttpClient =
                    if (BuildConfig.FLAVOR.toLowerCase().equals("dev")) getUnsafeOkHttpClient(
                        context
                    ) else okHttpClientBuilder.build()



                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }

        fun getUnsafeOkHttpClient(context: Context): OkHttpClient? {
            return try {


                val mCalendar: Calendar = GregorianCalendar()
                val mTimeZone = mCalendar.timeZone
                val mGMTOffset = mTimeZone.rawOffset
                val timezoneOffset = TimeUnit.MILLISECONDS.convert(
                    mGMTOffset.toLong(),
                    TimeUnit.MILLISECONDS
                )
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts =
                    arrayOf<TrustManager>(
                        object : X509TrustManager {
                            @Throws(CertificateException::class)
                            override fun checkClientTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                            ) {
                            }

                            @Throws(CertificateException::class)
                            override fun checkServerTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                            ) {
                            }

                            override fun getAcceptedIssuers(): Array<X509Certificate> {
                                return arrayOf()
                            }
                        }
                    )

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
      /*          builder.sslSocketFactory(sslSocketFactory)
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val request = original.newBuilder()
                            .header("platform", "Android")
                            .header("osVersion", android.os.Build.VERSION.SDK_INT.toString())
                            .header(
                                "device_id",
                                Settings.Secure.getString(
                                    context.getContentResolver(),
                                    Settings.Secure.ANDROID_ID
                                ).toString()
                            )
                            .header("appVersion", BuildConfig.VERSION_CODE.toString())
                            .header("time", System.currentTimeMillis().toString())
                            .header("Authorization", AppPreferences.getLoginToken())
                            .header("devicetoken", AppPreferences.getDeviceToken())
                            .header(
                                "lanuguage",
                                if (AppPreferences.getCurrentLanguage()
                                        .equals(GeneralConstants.LANGUAGE_NORWAY)
                                ) "nb" else "en"
                            )
                            .header("timezoneoffset", timezoneOffset.toString())
                            .method(original.method(), original.body())
                            .build()
                        chain.proceed(request)
                    }
                       if (BuildConfig.DEBUG)
                    `builder.addNetworkInterceptor(StethoInterceptor())
                    */




                builder.hostnameVerifier { hostname, session -> true }
                builder
                    .retryOnConnectionFailure(false)
                    .build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

    }


}