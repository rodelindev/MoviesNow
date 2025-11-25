package com.rodelindev.moviesnow.features.home.data.network.interceptor


import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .url(
                       request().url.newBuilder()
                            .addQueryParameter("api_key", apiKey)
                            .build()
                    ).build()
            )
        }
    }
}
