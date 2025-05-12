package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.BuildConfig
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
class AppModule {

    @Single
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Single
    @ApiKey
    fun provideApiKey(): String = BuildConfig.API_KEY

}

@Named
annotation class BaseUrl

@Named
annotation class ApiKey

