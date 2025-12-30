package com.rodelindev.moviesnow.features.authentication.data.datastore

interface TokenManager {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun deleteToken()
}
