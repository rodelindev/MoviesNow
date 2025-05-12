package com.rodelindev.moviesnow.home.domain.usecase

import androidx.paging.PagingData
import com.rodelindev.moviesnow.home.domain.model.Movie
import com.rodelindev.moviesnow.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetMoviesUseCase(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovieList()
    }
}