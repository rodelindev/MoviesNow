package com.rodelindev.moviesnow.features.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodelindev.moviesnow.features.authentication.domain.usecase.SignupWithEmailUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.ValidateEmailUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.ValidatePasswordUseCase
import com.rodelindev.moviesnow.features.authentication.presentation.utils.PasswordErrorParser
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupWithEmailUseCase: SignupWithEmailUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
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
                /*state = state.copy(
                    logIn = true
                )*/
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
        if (!validateEmailUseCase(state.username)) {
            state = state.copy(
                emailError = "El email no es valido"
            )
        }
        val passwordResult = validatePasswordUseCase(state.password)
        state = state.copy(
            passwordError = PasswordErrorParser.parseError(passwordResult)
        )

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                signupWithEmailUseCase(state.username, state.password).onSuccess {
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
