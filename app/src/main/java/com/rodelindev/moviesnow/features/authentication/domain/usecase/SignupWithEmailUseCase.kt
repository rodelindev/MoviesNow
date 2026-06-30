package com.rodelindev.moviesnow.features.authentication.domain.usecase

import com.rodelindev.moviesnow.features.authentication.domain.repository.IAuthRepository

class SignupWithEmailUseCase(
    private val repository: IAuthRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return repository.signup(username, password)
    }
}