package com.rodelindev.moviesnow.features.home.di

import com.rodelindev.moviesnow.features.MainViewModel
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMovieByIdUseCase
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMoviesUseCase
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailViewModel
import com.rodelindev.moviesnow.features.home.presentation.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    // View Model
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::MovieDetailViewModel)
    //Use case
    factoryOf(::GetMoviesUseCase)
    factoryOf(::GetMovieByIdUseCase)
}
