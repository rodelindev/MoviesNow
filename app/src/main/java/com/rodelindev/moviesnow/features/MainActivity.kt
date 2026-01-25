package com.rodelindev.moviesnow.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rodelindev.moviesnow.navigation.HomeRoute
import com.rodelindev.moviesnow.navigation.Login
import com.rodelindev.moviesnow.navigation.NavigationRoot
import com.rodelindev.moviesnow.navigation.NavigationRoute
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesNowTheme {
                NavigationRoot(
                    startDestination = getStartDestination(),
                    logout = viewModel::logout
                )
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {
        return when {
            viewModel.isLoggedIn -> HomeRoute
            else -> Login
        }
    }
}
