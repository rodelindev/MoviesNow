package com.rodelindev.moviesnow

import android.app.Application
import com.rodelindev.moviesnow.core.di.AppModule
import com.rodelindev.moviesnow.core.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class MoviesNowApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesNowApp)
            defaultModule()
            modules(
                AppModule().module,
                DataModule().module,
            )
        }
    }
}
