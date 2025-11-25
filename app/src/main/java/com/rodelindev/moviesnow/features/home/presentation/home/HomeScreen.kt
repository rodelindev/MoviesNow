@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.rodelindev.moviesnow.features.home.presentation.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rodelindev.moviesnow.core.presentation.components.CustomProgressIndicator
import com.rodelindev.moviesnow.core.presentation.components.ErrorView
import com.rodelindev.moviesnow.core.presentation.components.MovieCard
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onMovieClick: (Int) -> Unit,
    onLogout: () -> Unit
) {

    val moviesState = viewModel.state.collectAsLazyPagingItems()

    Scaffold { paddingValues ->
        when {
            moviesState.loadState.refresh is LoadState.Loading && moviesState.itemCount == 0 -> {
                CustomProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }
            moviesState.loadState.refresh is LoadState.NotLoading && moviesState.itemCount == 0 -> {
                CustomProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }
            moviesState.loadState.hasError -> {
                ErrorView(
                    message = "Error Network",
                    onRetry = {
                        moviesState.apply {
                            refresh()
                        }
                    }
                )
            }
            else -> {
                MovieListContent(
                    paddingValues = paddingValues,
                    movies = moviesState,
                    onMovieClick = onMovieClick,
                )
            }
        }
    }
}

@Composable
fun MovieListContent(
    paddingValues: PaddingValues,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 10.dp,
                top = 15.dp,
                bottom = 15.dp
            ),
            state = rememberLazyGridState()
        ) {
            items(
                count = movies.itemCount
            ) { index ->
                movies[index]?.let { movie ->
                    MovieCard(
                        movie = movie,
                        onMovieClick = onMovieClick,
                    )
                }
            }

            movies.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item(span = { GridItemSpan(2) }) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}
