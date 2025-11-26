package com.rodelindev.moviesnow.features.authentication.presentation.login

sealed interface LoginAction {
    data object Login: LoginAction
    data class PasswordChange(val password: String): LoginAction
    data class UserNameChange(val username: String): LoginAction
}