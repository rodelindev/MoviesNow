package com.rodelindev.moviesnow.features.home.domain.usecase

import com.rodelindev.moviesnow.features.home.domain.model.Movie
import com.rodelindev.moviesnow.features.home.domain.repository.IMoviesRepository

class GetMovieByIdUseCase(
    private val repository: IMoviesRepository,
) {
    suspend operator fun invoke(movieId: Int): Result<Movie> {
        return repository.getMovieById(movieId)
    }
}
