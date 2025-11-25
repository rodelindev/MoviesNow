package com.rodelindev.moviesnow.features.home.domain.usecase

import androidx.paging.PagingData
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import com.rodelindev.moviesnow.features.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovieList()
    }
}
