package com.rodelindev.moviesnow.features.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodelindev.moviesnow.features.authentication.domain.usecase.LoginWithEmailUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.ValidateEmailUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.ValidatePasswordUseCase
import com.rodelindev.moviesnow.features.authentication.presentation.utils.PasswordErrorParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.PasswordChange -> {
                state = state.copy(
                    password = action.password
                )
            }

            is LoginAction.UserNameChange -> {
                state = state.copy(
                    username = action.username
                )
            }

            LoginAction.Login -> {
                login()
            }
        }
    }

    private fun login() {
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
            viewModelScope.launch(Dispatchers.IO) {
                loginWithEmailUseCase(state.username, state.password).onSuccess {
                    state = state.copy(
                        isLoggedIn = true
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
