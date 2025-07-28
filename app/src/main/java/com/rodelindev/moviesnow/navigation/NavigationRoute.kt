package com.rodelindev.moviesnow.navigation

import kotlinx.serialization.Serializable


sealed interface NavigationRoute

@Serializable
data object HomeRoute: NavigationRoute

@Serializable
data class MovieDetail(val id: Int): NavigationRoute
