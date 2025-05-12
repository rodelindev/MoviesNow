package com.rodelindev.moviesnow.features.home.data.repository.mediator

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rodelindev.moviesnow.features.home.data.mapper.toListDomain
import com.rodelindev.moviesnow.features.home.data.network.services.MovieDBService
import com.rodelindev.moviesnow.features.home.domain.model.Movie


class MoviesPagingSource(
    private val api: MovieDBService,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: 1

        return try {
            val response = api.getMovieList(page = pageNumber)
            val movie = response.results.toListDomain()

            LoadResult.Page(
                data = movie,
                prevKey = if (pageNumber > 1) pageNumber.minus(1) else null,
                nextKey = if (response.page <= response.totalPages) pageNumber.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}