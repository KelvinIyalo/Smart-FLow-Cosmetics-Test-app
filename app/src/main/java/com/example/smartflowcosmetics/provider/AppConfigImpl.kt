package com.example.smartflowcosmetics.provider

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class AppConfigImpl : AppConfig {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    override val okHttpClient = OkHttpClient.Builder().apply {
        writeTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(loggingInterceptor)
        }
    }.build()

}