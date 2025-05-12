package com.rodelindev.moviesnow.home.domain.usecase

import com.rodelindev.moviesnow.home.domain.model.Movie
import com.rodelindev.moviesnow.home.domain.repository.MoviesRepository
import org.koin.core.annotation.Factory

@Factory
class GetMovieByIdUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Movie> {
        return repository.getMovieById(movieId)
    }
}