package com.rodelindev.moviesnow.features.home.di

import com.rodelindev.moviesnow.features.MainViewModel
import com.rodelindev.moviesnow.features.home.data.network.services.MovieDBService
import com.rodelindev.moviesnow.features.home.data.repository.MoviesRepositoryImpl
import com.rodelindev.moviesnow.features.home.domain.repository.MoviesRepository
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMovieByIdUseCase
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMoviesUseCase
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailViewModel
import com.rodelindev.moviesnow.features.home.presentation.home.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val homeModule = module {
    // Retrofit Service
    single<MovieDBService> { get<Retrofit>().create<MovieDBService>() }

    // Repository Implementation
    singleOf(::MoviesRepositoryImpl) { bind<MoviesRepository>() }

    // View Model
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::MovieDetailViewModel)

    //Use case
    factoryOf(::GetMoviesUseCase)
    factoryOf(::GetMovieByIdUseCase)
}
