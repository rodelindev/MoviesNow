package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.core.datastore.preferences.UserPreferences
import com.rodelindev.moviesnow.core.datastore.preferences.UserPreferencesImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val preferencesModule = module {
    single<UserPreferencesImpl>() bind(UserPreferences::class)
}
