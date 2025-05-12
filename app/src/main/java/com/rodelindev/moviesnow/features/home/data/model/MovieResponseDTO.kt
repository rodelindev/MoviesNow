package com.rodelindev.moviesnow.features.home.data.model


import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)