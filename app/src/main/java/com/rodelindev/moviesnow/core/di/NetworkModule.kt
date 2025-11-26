package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.features.home.data.network.interceptor.ApiKeyInterceptor
import com.rodelindev.moviesnow.features.home.data.network.services.MovieDBService
import com.rodelindev.moviesnow.features.home.data.repository.MoviesRepositoryImpl
import com.rodelindev.moviesnow.features.home.domain.repository.MoviesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named(Qualifier.BaseUrl)))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<MovieDBService> {
        get<Retrofit>().create<MovieDBService>()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<ApiKeyInterceptor>())
            .build()
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory { ApiKeyInterceptor(get(named(Qualifier.ApiKey))) }

    singleOf(::MoviesRepositoryImpl) { bind<MoviesRepository>() }
}
