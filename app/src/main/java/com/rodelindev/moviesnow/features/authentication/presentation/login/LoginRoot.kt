package com.rodelindev.moviesnow.features.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {

}

@Preview
@Composable
private fun LoginPreview() {
    MoviesNowTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}