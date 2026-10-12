@file:OptIn(ExperimentalMaterial3Api::class)

package com.rodelindev.moviesnow.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodelindev.moviesnow.R
import com.rodelindev.moviesnow.core.presentation.CustomProgressIndicator
import com.rodelindev.moviesnow.core.presentation.ErrorView
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onLogout: () -> Unit,
    onBack: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when {
        state.isLoading -> {
            CustomProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
        }

        state.errorMessage != null -> {
            ErrorView(
                message = state.errorMessage ?: stringResource(R.string.unknown_error_message),
                onRetry = { },
                modifier = Modifier.fillMaxSize()
            )
        }

        else -> {
            ProfileContent(
                state = state,
                onLogout = onLogout,
                onNavigateUp = onBack
            )
        }
    }
}

@Composable
fun ProfileContent(
    state: ProfileState,
    onLogout: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(0.9f),
            ) {
                Text(text = "Log Out")
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MoviesNowTheme {
        ProfileContent(
            state = ProfileState(),
            onLogout = {},
            onNavigateUp = {}
        )
    }
}
