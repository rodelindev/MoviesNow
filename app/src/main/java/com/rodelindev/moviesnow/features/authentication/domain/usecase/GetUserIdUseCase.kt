package com.rodelindev.moviesnow.features.authentication.domain.usecase

import com.rodelindev.moviesnow.features.authentication.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class GetUserIdUseCase(
    private val repository: IAuthRepository
) {
    operator fun invoke(): Flow<String?> {
        return repository.getUserId()
    }
}
