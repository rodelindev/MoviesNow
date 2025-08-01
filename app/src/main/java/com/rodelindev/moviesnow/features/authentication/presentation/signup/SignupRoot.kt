package com.rodelindev.moviesnow.features.authentication.presentation.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupRoot(
    viewModel: SignupViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignupScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SignupScreen(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
) {

}

@Preview
@Composable
private fun SignupPreview() {
    MoviesNowTheme {
        SignupScreen(
            state = SignupState(),
            onAction = {}
        )
    }
}