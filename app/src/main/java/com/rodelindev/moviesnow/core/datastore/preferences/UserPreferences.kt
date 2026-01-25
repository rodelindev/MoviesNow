package com.rodelindev.moviesnow.core.datastore.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun saveToken(token: String)
    fun getSessionId(): Flow<String?>
    suspend fun deleteToken()
    fun isUserLoggedIn(): Boolean
}
