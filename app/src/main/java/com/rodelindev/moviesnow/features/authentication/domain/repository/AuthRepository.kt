package com.rodelindev.moviesnow.features.authentication.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun signup(username: String, password: String): Result<Unit>
    fun getUserId(): Flow<String?>
    fun isUserLoggedIn(): Boolean
    suspend fun logout()
}