package com.rodelindev.moviesnow.features.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodelindev.moviesnow.features.authentication.domain.usecase.SignupUseCases
import com.rodelindev.moviesnow.features.authentication.presentation.utils.PasswordErrorParser
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUseCases: SignupUseCases
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.UserNameChange -> {
                state = state.copy(
                    username = action.username
                )
            }
            is SignupAction.PasswordChange -> {
                state = state.copy(
                    password = action.password
                )
            }
            SignupAction.LogIn -> {
                state = state.copy(
                    logIn = true
                )
            }
            SignupAction.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        state = state.copy(
            emailError = null,
            passwordError = null
        )
        if (!signupUseCases.validateEmailUseCase(state.username)) {
            state = state.copy(
                emailError = "El email no es valido"
            )
        }
        val passwordResult = signupUseCases.validatePasswordUseCase(state.password)
        state = state.copy(
            passwordError = PasswordErrorParser.parseError(passwordResult)
        )

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                signupUseCases.signupWithEmailUseCase(state.username, state.password).onSuccess {
                    state = state.copy(
                        isSignedIn = true
                    )
                }.onFailure {
                    state = state.copy(
                        emailError = it.message
                    )
                }
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }
}
