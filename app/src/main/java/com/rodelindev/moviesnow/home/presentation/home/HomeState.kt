package com.rodelindev.moviesnow.home.presentation.home

data class HomeState(
    val isLoading: Boolean = false,
    val movie: String = ""
)