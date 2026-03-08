@file:OptIn(ExperimentalMaterial3Api::class)

package com.rodelindev.moviesnow.features.home.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.rodelindev.moviesnow.R
import com.rodelindev.moviesnow.core.presentation.components.ErrorView
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    onBack: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.errorMessage != null -> {
            ErrorView(
                message = state.errorMessage ?: stringResource(R.string.unknown_error_message),
                onRetry = { },
                modifier = Modifier.fillMaxSize()
            )
        }

        else -> {
            MovieDetailContent(
                movie = state.movie,
                onNavigateUp = onBack,
            )
        }
    }
}

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movie: Movie?,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_favorite),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            AsyncImage(
                model = movie?.poster,
                contentDescription = movie?.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(horizontal = 16.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                /*.width(180.dp)
                .aspectRatio(2 / 3f)*/
            )
            Text(
                text = movie?.title ?: "",
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    MoviesNowTheme {
        MovieDetailContent(
            movie = Movie(
                adult = false,
                backdrop = "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg",
                genreIds = null,
                id = 550,
                originalLanguage = "en",
                originalTitle = "Fight Club",
                overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel.",
                popularity = 61.416,
                poster = "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
                releaseDate = "1999-10-15",
                title = "Fight Club",
                video = false,
                voteAverage = 8.433,
                voteCount = 26280
            ),
            onNavigateUp = {}
        )
    }
}
