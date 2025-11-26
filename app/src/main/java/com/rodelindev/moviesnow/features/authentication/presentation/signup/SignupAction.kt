package com.rodelindev.moviesnow.features.authentication.presentation.signup

sealed interface SignupAction {
    data object SignUp: SignupAction
    data object LogIn: SignupAction
    data class PasswordChange(val password: String): SignupAction
    data class UserNameChange(val username: String): SignupAction
}