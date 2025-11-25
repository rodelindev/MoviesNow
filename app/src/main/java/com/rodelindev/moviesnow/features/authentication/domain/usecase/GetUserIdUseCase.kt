package com.rodelindev.moviesnow.features.authentication.domain.usecase

import com.rodelindev.moviesnow.features.authentication.domain.repository.AuthRepository

class GetUserIdUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getUserId()
    }
}
