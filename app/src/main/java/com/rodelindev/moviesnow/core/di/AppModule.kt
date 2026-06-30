package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.BuildConfig
import com.rodelindev.moviesnow.features.authentication.di.authenticationModule
import com.rodelindev.moviesnow.features.home.di.homeModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(
        listOf(
            networkModule,
            preferencesModule,
            authenticationModule,
            homeModule
        )
    )

    single(named(Qualifier.BaseUrl)) { BuildConfig.BASE_URL }
    single(named(Qualifier.ApiKey)) { BuildConfig.API_KEY }
}

enum class Qualifier {
    BaseUrl,
    ApiKey
}
