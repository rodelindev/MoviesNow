package com.rodelindev.moviesnow.features.home.presentation.detail

import com.rodelindev.moviesnow.features.home.domain.model.Movie

data class MovieDetailState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)