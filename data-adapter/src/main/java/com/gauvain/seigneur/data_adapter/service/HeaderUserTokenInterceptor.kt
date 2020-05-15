package com.gauvain.seigneur.data_adapter.service

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class HeaderUserTokenInterceptor(private val userSessionToken: String?) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        userSessionToken?.let {
            builder.addHeader("User-Token", it)
        }
        return chain.proceed(builder.build())
    }
}