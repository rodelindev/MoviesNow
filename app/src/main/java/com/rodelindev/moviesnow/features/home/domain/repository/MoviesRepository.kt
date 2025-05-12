package com.rodelindev.moviesnow.features.home.domain.repository

import androidx.paging.PagingData
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovieList(): Flow<PagingData<Movie>>

    suspend fun getMovieById(movieId: Int): Result<Movie>

}