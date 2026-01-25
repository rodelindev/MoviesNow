package com.rodelindev.moviesnow.features.authentication.data.repository

import com.rodelindev.moviesnow.core.datastore.preferences.UserPreferences
import com.rodelindev.moviesnow.features.authentication.data.model.AuthRequest
import com.rodelindev.moviesnow.features.authentication.data.network.AuthApi
import com.rodelindev.moviesnow.features.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class AuthenticationRepositoryImpl(
    private val api: AuthApi,
    private val dataStore: UserPreferences
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Unit> {
        return try {
            val response = api.signIn(
                request = AuthRequest(
                    username = username,
                    password = password
                )
            )
            dataStore.saveToken(response.token)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signup(username: String, password: String): Result<Unit> {
        return try {
            api.signUp(
                request = AuthRequest(
                    username = username,
                    password = password
                )
            )
            login(username, password)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getUserId(): Flow<String?> {
        return dataStore.getSessionId()
    }

    override fun isUserLoggedIn(): Boolean {
        return runBlocking {
            dataStore.isUserLoggedIn()
        }
    }

    override suspend fun logout() {
        dataStore.deleteToken()
    }
}
