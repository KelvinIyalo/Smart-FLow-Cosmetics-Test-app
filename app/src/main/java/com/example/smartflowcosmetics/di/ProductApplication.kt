package com.example.smartflowcosmetics.di

import android.app.Application
import com.example.smartflowcosmetics.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ProductApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(CustomTree())
        }
    }

    class CustomTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            super.log(priority, tag.plus("_XXXXX"), message, t)
        }

    }
}