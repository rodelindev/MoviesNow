package com.rodelindev.moviesnow.features.authentication.domain.usecase

import com.rodelindev.moviesnow.features.authentication.domain.repository.AuthRepository

class SignupWithEmailUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return repository.signup(username, password)
    }
}