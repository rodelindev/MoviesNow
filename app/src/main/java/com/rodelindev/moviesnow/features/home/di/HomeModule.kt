package com.rodelindev.moviesnow.features.home.di

import com.rodelindev.moviesnow.features.MainViewModel
import com.rodelindev.moviesnow.features.home.data.network.services.MovieDBService
import com.rodelindev.moviesnow.features.home.data.repository.MoviesRepositoryImpl
import com.rodelindev.moviesnow.features.home.domain.repository.MoviesRepository
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMovieByIdUseCase
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMoviesUseCase
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailViewModel
import com.rodelindev.moviesnow.features.home.presentation.home.HomeViewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.create

val homeModule = module {
    // Retrofit Service
    single<MovieDBService> { get<Retrofit>().create<MovieDBService>() }

    // Repositories
    single<MoviesRepositoryImpl>() bind(MoviesRepository::class)

    // Use Cases
    factory<GetMoviesUseCase>()
    factory<GetMovieByIdUseCase>()

    // View Models
    viewModel<MainViewModel>()
    viewModel<HomeViewModel>()
    viewModel<MovieDetailViewModel>()
}
