package com.rodelindev.moviesnow.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodelindev.moviesnow.features.authentication.presentation.login.LoginRoot
import com.rodelindev.moviesnow.features.authentication.presentation.signup.SignupRoot
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailScreen
import com.rodelindev.moviesnow.features.home.presentation.home.MovieListScreen

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { - it } },
        popEnterTransition = { slideInHorizontally { - it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        composable<Login> {
            LoginRoot()
        }
        composable<Signup> {
            SignupRoot()
        }
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
