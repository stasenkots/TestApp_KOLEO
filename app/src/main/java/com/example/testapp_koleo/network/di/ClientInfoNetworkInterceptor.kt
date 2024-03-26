package com.example.testapp_koleo.network.di

import com.example.testapp_koleo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientInfoNetworkInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().apply {
            addHeader(VERSION_HEADER, BuildConfig.KOLEO_API_VERSION)
        }

        return chain.proceed(requestBuilder.build())
    }


    private companion object {
        private const val VERSION_HEADER = "X-KOLEO-Version"
    }
}