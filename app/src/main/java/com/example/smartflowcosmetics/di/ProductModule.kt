package com.example.smartflowcosmetics.di

import com.example.smartflowcosmetics.BuildConfig
import com.example.smartflowcosmetics.provider.AppConfig
import com.example.smartflowcosmetics.provider.AppConfigImpl
import com.example.smartflowcosmetics.remote.CosmeticServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Singleton
    @Provides
    fun provideAppConfig() = AppConfigImpl() as AppConfig

    @Singleton
    @Provides
    fun provideFloatService(appConfig: AppConfig): CosmeticServices {
        val baseUrl = BuildConfig.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(appConfig.okHttpClient)
            .build()
            .create(CosmeticServices::class.java)
    }

}