package com.rodelindev.moviesnow.features.authentication.presentation.signup

data class SignupState(
    val username: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false,
    val logIn: Boolean = false
)