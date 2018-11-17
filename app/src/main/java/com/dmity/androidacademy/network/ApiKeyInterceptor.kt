package com.dmity.androidacademy.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String): Interceptor {

    companion object {
        private const val PARAM_API_KEY = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestWithoutApiKey = chain.request()

        val url = requestWithoutApiKey.url()
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, apiKey)
                .build()

        val requestWithAttachedApiKey = requestWithoutApiKey
                .newBuilder()
                .url(url)
                .build()

        return chain.proceed(requestWithAttachedApiKey)

    }
}