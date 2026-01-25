package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.core.datastore.preferences.UserPreferences
import com.rodelindev.moviesnow.core.datastore.preferences.UserPreferencesImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val preferencesModule = module {
    singleOf(::UserPreferencesImpl) { bind<UserPreferences>()  }
}
