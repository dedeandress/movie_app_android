package com.dedeandres.scaffoldprojectmvvmcoroutines.common

import android.content.Context
import android.content.Intent
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.sharedpref.SharedPrefs
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.dedeandres.scaffoldproject.common.KeyUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor (
    private val context: Context,
    private val sharedPrefs: SharedPrefs
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder().method(original.method(), original.body())

        setAuthHeader(builder, sharedPrefs.accessToken)

        val newRequest = builder.build()
        val response = chain.proceed(newRequest)

        if(response.code() == NetworkException.CODE_UNAUTHORIZED) {
            sharedPrefs.clear()
            holdLogin()
        }

        return response
    }

    private fun setAuthHeader(builder: Request.Builder, token: String?) {
        token?.let { builder.header(KeyUtils.AUTHORIZATION, it) }
    }

    private fun holdLogin() {
        val intent = Intent(KeyUtils.UN_AUTHORIZATION_LOGOUT)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

}