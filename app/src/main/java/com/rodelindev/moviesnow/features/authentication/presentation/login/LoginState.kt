package com.rodelindev.moviesnow.features.authentication.presentation.login

data class LoginState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)