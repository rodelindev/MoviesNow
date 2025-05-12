package com.rodelindev.moviesnow.home.data.network.interceptor


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

    /*override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(
            "Authorization",
            "Bearer ${BuildConfig.API_KEY}"
        ).build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }*/
}
