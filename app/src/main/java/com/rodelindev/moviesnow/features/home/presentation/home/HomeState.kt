package com.rodelindev.moviesnow.features.home.presentation.home

data class HomeState(
    val isLoading: Boolean = false,
    val movie: String = ""
)