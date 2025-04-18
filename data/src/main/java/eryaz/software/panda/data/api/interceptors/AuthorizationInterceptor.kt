package eryaz.software.panda.data.api.interceptors

import eryaz.software.panda.data.persistence.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder().apply {
                addHeader("Content-Type", "application/json; charset=UTF-8")
                if (SessionManager.token.isNotEmpty())
                    addHeader("Authorization", SessionManager.token)
            }.build()
        return chain.proceed(request)
    }
}
