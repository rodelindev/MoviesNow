package com.rodelindev.moviesnow.features.home.data.mapper

import com.rodelindev.moviesnow.features.home.data.model.MovieDTO
import com.rodelindev.moviesnow.features.home.domain.model.Movie

fun MovieDTO.toDomain(): Movie {
    return Movie(
        adult = this.adult,
        backdrop = this.backdropPath.let { "https://image.tmdb.org/t/p/w780/$it" },
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}


fun List<MovieDTO>.toListDomain(): List<Movie> {
    return this.map { movieDTO ->
        movieDTO.toDomain()
    }
}
