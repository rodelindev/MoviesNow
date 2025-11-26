package com.rodelindev.moviesnow.features.authentication.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun signup(username: String, password: String): Result<Unit>
    suspend fun getUserId(): String?
    suspend fun logout()
}