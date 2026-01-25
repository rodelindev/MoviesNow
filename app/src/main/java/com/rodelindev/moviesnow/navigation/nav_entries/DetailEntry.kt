package com.rodelindev.moviesnow.navigation.nav_entries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailScreen
import com.rodelindev.moviesnow.features.home.presentation.detail.MovieDetailViewModel
import com.rodelindev.moviesnow.navigation.MovieDetail
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.detailEntry(
    backStack: NavBackStack<NavKey>,
) {
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