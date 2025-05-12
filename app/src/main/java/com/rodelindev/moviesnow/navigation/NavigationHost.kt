package com.rodelindev.moviesnow.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodelindev.moviesnow.home.presentation.detail.MovieDetailScreen
import com.rodelindev.moviesnow.home.presentation.home.MovieListScreen
import com.rodelindev.moviesnow.navigation.NavigationRoute.HomeRoute
import com.rodelindev.moviesnow.navigation.NavigationRoute.MovieDetail

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        enterTransition = { slideInHorizontally(tween(500)) { it } },
        exitTransition = { slideOutHorizontally(tween(500)) { - it } },
        popEnterTransition = { slideInHorizontally(tween(500)) { - it } },
        popExitTransition = { slideOutHorizontally(tween(500)) { it } }
    ) {
        composable<HomeRoute> {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate(
                        route = MovieDetail(movieId)
                    )
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