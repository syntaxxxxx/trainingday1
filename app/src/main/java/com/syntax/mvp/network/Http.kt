package com.syntax.mvp.network

import com.syntax.mvp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Http {

    val instance: Retrofit by lazy {
        val httpLogingInterceptor = HttpLoggingInterceptor()
        httpLogingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var okHttp = OkHttpClient.Builder()
            .addInterceptor(httpLogingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofit: ApiService
        get() = instance.create(ApiService::class.java)
}