package com.rodelindev.moviesnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.rodelindev.moviesnow.navigation.NavigationRoot
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesNowTheme {
                NavigationRoot(
                    navController = rememberNavController()
                )
            }
        }
    }
}
