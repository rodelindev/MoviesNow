package com.rodelindev.moviesnow.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed interface NavigationRoute: NavKey

// Home Routes
@Serializable
data object HomeRoute: NavigationRoute

@Serializable
data class MovieDetail(val movieId: Int): NavigationRoute

// Authentication Routes
@Serializable
data object LoginRoute: NavigationRoute

@Serializable
data object SignupRoute: NavigationRoute

