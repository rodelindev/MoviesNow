package com.rodelindev.moviesnow.navigation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailScreen
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailViewModel
import com.rodelindev.moviesnow.features.home.presentation.home.MovieListScreen
import com.rodelindev.moviesnow.navigation.nav_entries.authNavEntries
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationRoot(
    startDestination: NavigationRoute,
    logout: () -> Unit,
) {
    val backStack = rememberNavBackStack(startDestination)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(animationSpec = tween(300)) + fadeIn(),
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(300)
                ) + fadeOut()
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(animationSpec = tween(300)) + fadeIn(),
                slideOutHorizontally(animationSpec = tween(300)) + fadeOut()
            )
        },
        entryProvider = entryProvider {
            //Authentication Routes
            authNavEntries(backStack)
            //Home Routes
            entry<HomeRoute> {
                MovieListScreen(
                    onMovieClick = {
                        backStack.add(MovieDetail(it))
                    },
                    onLogout = {
                        logout()
                        backStack.clear()
                        backStack.add(Login)
                    }
                )
            }
            entry<MovieDetail> { createdKey ->
                val detailViewModel = koinViewModel<MovieDetailViewModel> {
                    parametersOf(createdKey)
                }
                /*val detailViewModel = koinViewModel<MovieDetailViewModel>(
                    parameters = {
                        parametersOf(createdKey)
                    }
                )*/
                MovieDetailScreen(
                    viewModel = detailViewModel,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
