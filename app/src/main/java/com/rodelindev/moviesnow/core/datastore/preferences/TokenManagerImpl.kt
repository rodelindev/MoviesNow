package com.rodelindev.moviesnow.core.datastore.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.rodelindev.moviesnow.core.datastore.TOKEN_KEY
import com.rodelindev.moviesnow.core.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class TokenManagerImpl(
    private val context: Context,
) : TokenManager {

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override fun getSessionId(): Flow<String?> {
        return context.dataStore.data
            .map { preferences -> preferences[TOKEN_KEY].orEmpty() }
    }

    override suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    override fun isUserLoggedIn(): Boolean = runBlocking {
        context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY].isNullOrBlank()
        }.first()
    }
}
