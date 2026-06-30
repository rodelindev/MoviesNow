package com.rodelindev.moviesnow.features.authentication.domain.usecase

import com.rodelindev.moviesnow.features.authentication.domain.repository.IAuthRepository

class IsUserLoggedInUseCase(
    private val repository: IAuthRepository
) {
    operator fun invoke() = repository.isUserLoggedIn()
}
