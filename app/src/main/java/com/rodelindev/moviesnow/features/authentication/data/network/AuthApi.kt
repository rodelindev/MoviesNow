package com.rodelindev.moviesnow.features.authentication.data.network

import com.rodelindev.moviesnow.features.authentication.data.model.AuthRequest
import com.rodelindev.moviesnow.features.authentication.data.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("")
    suspend fun signIn(
        @Body request: AuthRequest
    ): TokenResponse

    @POST("")
    suspend fun signUp(
        @Body request: AuthRequest
    )
}