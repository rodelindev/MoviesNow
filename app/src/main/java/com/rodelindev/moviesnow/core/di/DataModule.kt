package com.rodelindev.moviesnow.core.di

import com.rodelindev.moviesnow.features.home.data.network.interceptor.ApiKeyInterceptor
import com.rodelindev.moviesnow.features.home.data.network.services.MovieDBService
import com.rodelindev.moviesnow.features.home.data.repository.MoviesRepositoryImpl
import com.rodelindev.moviesnow.features.home.domain.repository.MoviesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Module
class DataModule {

    @Single
    fun provideRetrofit(
        @BaseUrl apiEndpoint: String,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiEndpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Single
    fun provideOkHttpClient(
        interceptor: ApiKeyInterceptor,
        loginInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Single
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Factory
    fun provideApiKeyInterceptor(@ApiKey apiKey: String): ApiKeyInterceptor {
        return ApiKeyInterceptor(apiKey = apiKey)
    }

    @Single
    fun provideRickAndMortyApi(retrofit: Retrofit): MovieDBService {
        return retrofit.create(MovieDBService::class.java)
    }

    @Factory
    fun provideMoviesRepository(movieService: MovieDBService): MoviesRepository {
        return MoviesRepositoryImpl(
            movieService = movieService
        )
    }
}