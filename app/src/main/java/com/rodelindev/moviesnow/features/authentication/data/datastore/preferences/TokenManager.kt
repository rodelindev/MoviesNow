package com.rodelindev.moviesnow.features.authentication.data.datastore.preferences

import kotlinx.coroutines.flow.Flow

interface TokenManager {
    suspend fun saveToken(token: String)
    fun getSessionId(): Flow<String?>
    suspend fun deleteToken()
    fun isUserLoggedIn(): Boolean
}
