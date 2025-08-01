@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.rodelindev.moviesnow.core.presentation.components


import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rodelindev.moviesnow.features.home.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (Int) -> Unit,
) {
    Card(
        modifier = modifier.clickable { onMovieClick(movie.id) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .clip(RoundedCornerShape(10.dp)),
            )
            Text(
                text = movie.title,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        vertical = 10.dp,
                        horizontal = 15.dp
                    )
            )
        }
    }

}

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(
        movie = Movie(
            adult = false,
            backdrop = "https://image.tmdb.org/t/p/w185/9nhjGaFLKtddDPtPaX5EmKqsWdH.jpg",
            genreIds = listOf(10749, 878, 53),
            id = 950396,
            originalLanguage = "en",
            originalTitle = "The Gorge",
            overview = "Two highly trained operatives grow close from a distance after being " +
                    "sent to guard opposite sides of a mysterious gorge. When an evil below " +
                    "emerges, they must work together to survive what lies within.",
            popularity = 45.448,
            poster = "https://image.tmdb.org/t/p/w185/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg",
            releaseDate = "2025-02-13",
            title = "The Gorge",
            video = false,
            voteAverage = 7.778,
            voteCount = 1869
        ),
        onMovieClick = {},
    )
}