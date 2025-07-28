@file:OptIn(ExperimentalMaterial3Api::class)

package com.rodelindev.moviesnow.features.home.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rodelindev.moviesnow.core.presentation.components.ErrorView
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = koinViewModel(),
    onNavigateUp: () -> Unit,
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

        state.error != null -> {
            ErrorView(
                message = state.error ?: "Unknown error occurred",
                onRetry = { },
                modifier = Modifier.fillMaxSize()
            )
        }

        else -> {
            MovieDetailContent(
                movie = state.movie,
                onNavigateUp = onNavigateUp,
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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie?.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
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
                    .height(500.dp)
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
                textAlign = TextAlign.Center,

            )
        }
    }
}