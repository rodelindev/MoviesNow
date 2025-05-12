package com.rodelindev.moviesnow.home.presentation.detail

import com.rodelindev.moviesnow.home.domain.model.Movie

data class MovieDetailState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)