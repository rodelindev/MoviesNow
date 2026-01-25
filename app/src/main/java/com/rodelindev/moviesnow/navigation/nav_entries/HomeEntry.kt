package com.rodelindev.moviesnow.navigation.nav_entries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.rodelindev.moviesnow.features.home.presentation.home.MovieListScreen
import com.rodelindev.moviesnow.navigation.HomeRoute
import com.rodelindev.moviesnow.navigation.Login
import com.rodelindev.moviesnow.navigation.MovieDetail

fun EntryProviderScope<NavKey>.homeEntry(
    backStack: NavBackStack<NavKey>,
    logout: () -> Unit,
) {
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
}