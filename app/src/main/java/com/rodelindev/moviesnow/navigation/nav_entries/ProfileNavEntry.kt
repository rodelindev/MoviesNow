package com.rodelindev.moviesnow.navigation.nav_entries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.rodelindev.moviesnow.features.profile.presentation.ProfileScreen
import com.rodelindev.moviesnow.navigation.LoginRoute
import com.rodelindev.moviesnow.navigation.ProfileRoute

fun EntryProviderScope<NavKey>.profileNavEntry(
    backStack: NavBackStack<NavKey>,
    logout: () -> Unit,
) {
    entry<ProfileRoute> {
        ProfileScreen(
            onLogout = {
                logout()
                backStack.clear()
                backStack.add(LoginRoute)
            },
            onBack = backStack::removeLastOrNull
        )
    }
}
