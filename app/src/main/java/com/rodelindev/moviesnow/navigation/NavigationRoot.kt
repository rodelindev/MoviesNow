package com.rodelindev.moviesnow.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailScreen
import com.rodelindev.moviesnow.features.home.presentation.home.MovieListScreen

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        enterTransition = { slideInHorizontally(tween(300)) { it } },
        exitTransition = { slideOutHorizontally(tween(300)) { - it } },
        popEnterTransition = { slideInHorizontally(tween(300)) { - it } },
        popExitTransition = { slideOutHorizontally(tween(300)) { it } }
    ) {
        composable<HomeRoute> {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate(MovieDetail(movieId))
                }
            )
        }
        composable<MovieDetail> {
            MovieDetailScreen(
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}