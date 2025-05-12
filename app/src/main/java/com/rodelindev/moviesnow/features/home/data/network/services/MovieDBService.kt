package com.rodelindev.moviesnow.features.home.data.network.services

import com.rodelindev.moviesnow.features.home.data.model.MovieDTO
import com.rodelindev.moviesnow.features.home.data.model.MovieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    @GET("discover/movie?sorted=popularity.desc")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): MovieResponseDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): MovieDTO
}