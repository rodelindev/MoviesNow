package com.rodelindev.moviesnow.features.home.domain.model


data class Movie(
    val adult: Boolean,
    val backdrop: String? = null,
    val genreIds: List<Int>? = emptyList(),
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val poster: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)