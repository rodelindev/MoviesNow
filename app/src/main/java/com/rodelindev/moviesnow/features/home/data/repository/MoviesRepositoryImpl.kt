package com.rodelindev.moviesnow.features.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rodelindev.moviesnow.features.home.data.extensions.resultOf
import com.rodelindev.moviesnow.features.home.data.mapper.toDomain
import com.rodelindev.moviesnow.features.home.data.network.services.MovieDBService
import com.rodelindev.moviesnow.features.home.data.repository.mediator.MoviesPagingSource
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import com.rodelindev.moviesnow.features.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val movieService: MovieDBService
): MoviesRepository {

    override fun getMovieList(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 3
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    api = movieService,
                )
            }
        ).flow
    }

    override suspend fun getMovieById(movieId: Int): Result<Movie> = resultOf {
        movieService.getMovieById(movieId = movieId).toDomain()
    }
}