package com.rodelindev.moviesnow

import android.app.Application
import com.rodelindev.moviesnow.core.di.appModule
import com.rodelindev.moviesnow.core.di.dispatchersModule
import com.rodelindev.moviesnow.core.di.networkModule
import com.rodelindev.moviesnow.features.authentication.di.authenticationModule
import com.rodelindev.moviesnow.features.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(
                appModule,
                networkModule,
                dispatchersModule,
                authenticationModule,
                homeModule,
            )
        }
    }
}
