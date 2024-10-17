package com.meli.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val headers = chain.request().headers
            .newBuilder()
            .add("name", "value")
            .build()

        val request = chain.request().newBuilder().headers(headers).build()
        return chain.proceed(request)
    }
}