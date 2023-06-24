package com.bashirli.lazastore.data.service

import com.bashirli.lazastore.common.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request().newBuilder()
        val token=tokenManager.getToken()

        request.addHeader("Authorization","Bearer $token")

        return chain.proceed(request.build())
    }
}