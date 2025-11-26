package com.rodelindev.moviesnow.navigation.navigationgraph

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.rodelindev.moviesnow.features.authentication.presentation.login.LoginRoot
import com.rodelindev.moviesnow.features.authentication.presentation.signup.SignupRoot
import com.rodelindev.moviesnow.navigation.HomeRoute
import com.rodelindev.moviesnow.navigation.Login
import com.rodelindev.moviesnow.navigation.Signup

fun EntryProviderScope<NavKey>.authenticationGraph(
    backStack: NavBackStack<NavKey>
) {
    entry<Login> {
        LoginRoot(
            onLogin = {
                backStack.clear()
                backStack.add(HomeRoute)
            },
            onSignup = {
                backStack.add(Signup)
            }
        )
    }
    entry<Signup> {
        SignupRoot(
            onSignIn = {
                backStack.clear()
                backStack.add(HomeRoute)
            },
            onLogin = {
                backStack.removeLastOrNull()
            }
        )
    }
}
