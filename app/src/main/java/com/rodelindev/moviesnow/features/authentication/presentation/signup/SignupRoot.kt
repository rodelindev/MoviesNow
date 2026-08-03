package com.rodelindev.moviesnow.features.authentication.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.rodelindev.moviesnow.R
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupRoot(
    viewModel: SignupViewModel = koinViewModel(),
    onSignIn: () -> Unit,
    onBack: () -> Unit,
) {
    val state = viewModel.state

    LaunchedEffect(state.isSignedIn) {
        if (state.isSignedIn) {
            onSignIn()
        }
    }

    SignupScreen(
        state = state,
        onAction = viewModel::onAction,
        onSignIn = onSignIn,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    onSignIn: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.username,
                    onValueChange = { onAction(SignupAction.UserNameChange(it)) },
                    placeholder = {
                        Text("User name")
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onAction(SignupAction.PasswordChange(it)) },
                    placeholder = {
                        Text("Password")
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                Button(
                    onClick = dropUnlessResumed { onSignIn() },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text("Sign Up")
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignupPreview() {
    MoviesNowTheme {
        SignupScreen(
            state = SignupState(),
            onAction = {},
            onSignIn = {},
            onBack = {}
        )
    }
}
