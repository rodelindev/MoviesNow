package com.rodelindev.moviesnow.navigation.nav_entries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.rodelindev.moviesnow.features.authentication.presentation.login.LoginRoot
import com.rodelindev.moviesnow.features.authentication.presentation.signup.SignupRoot
import com.rodelindev.moviesnow.navigation.HomeRoute
import com.rodelindev.moviesnow.navigation.LoginRoute
import com.rodelindev.moviesnow.navigation.SignupRoute

fun EntryProviderScope<NavKey>.authNavEntry(
    backStack: NavBackStack<NavKey>
) {
    entry<LoginRoute> {
        LoginRoot(
            onLogin = {
                backStack.clear()
                backStack.add(HomeRoute)
            },
            onSignup = {
                backStack.add(SignupRoute)
            }
        )
    }
    entry<SignupRoute> {
        SignupRoot(
            onSignIn = {
                backStack.clear()
                backStack.add(HomeRoute)
            },
            onLogin = backStack::removeLastOrNull
        )
    }
}
