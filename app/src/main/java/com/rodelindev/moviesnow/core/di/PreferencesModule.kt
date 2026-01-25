package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.core.datastore.preferences.TokenManager
import com.rodelindev.moviesnow.core.datastore.preferences.TokenManagerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val preferencesModule = module {

    singleOf(::TokenManagerImpl) { bind<TokenManager>()  }
}
