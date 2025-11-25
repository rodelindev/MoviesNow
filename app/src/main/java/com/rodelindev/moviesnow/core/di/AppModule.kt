package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.BuildConfig
import com.rodelindev.moviesnow.core.di.Qualifier.ApiKey
import com.rodelindev.moviesnow.core.di.Qualifier.BaseUrl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(BaseUrl)) { BuildConfig.BASE_URL }
    single(named(ApiKey)) { BuildConfig.API_KEY }
}

enum class Qualifier {
    BaseUrl,
    ApiKey,
    Main,
    IO
}
