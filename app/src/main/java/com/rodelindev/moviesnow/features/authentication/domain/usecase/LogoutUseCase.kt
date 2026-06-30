package com.rodelindev.moviesnow.features.authentication.domain.usecase

import com.rodelindev.moviesnow.features.authentication.domain.repository.IAuthRepository

class LogoutUseCase(
    private val repository: IAuthRepository
) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}
