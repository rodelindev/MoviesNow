package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.core.di.Qualifier.IO
import com.rodelindev.moviesnow.core.di.Qualifier.Main
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single(named(Main)) { Dispatchers.Main }
    single(named(IO)) { Dispatchers.IO }
}
