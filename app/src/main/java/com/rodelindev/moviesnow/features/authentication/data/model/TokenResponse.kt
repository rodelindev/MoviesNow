package com.rodelindev.moviesnow.features.authentication.data.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("request_token")
    val token: String
)