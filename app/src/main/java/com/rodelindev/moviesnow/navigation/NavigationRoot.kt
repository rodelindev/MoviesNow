package com.rodelindev.moviesnow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailScreen
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailViewModel
import com.rodelindev.moviesnow.features.home.presentation.home.MovieListScreen
import com.rodelindev.moviesnow.navigation.components.enterTransitionSpec
import com.rodelindev.moviesnow.navigation.components.popTransitionSpec
import com.rodelindev.moviesnow.navigation.nav_entries.authNavEntry
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
        transitionSpec = { enterTransitionSpec() },
        popTransitionSpec = { popTransitionSpec() },
        entryProvider = entryProvider {
            //Authentication Routes
            authNavEntry(backStack)
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
                MovieDetailScreen(
                    viewModel = detailViewModel,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
