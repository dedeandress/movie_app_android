package com.dedeandres.scaffoldprojectmvvmcoroutines.common.sharedpref

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.SessionState

class SharedPrefs(
    private val sharedPreferences: SharedPreferences
) : BaseSharedPreferences() {

    val accessTokenWithPrefix: String?
        get() = accessToken.takeIf { it.isNotEmpty() }?.let {
            StringBuilder().append(PREFIX_ACCESS_TOKEN).append(" ").append(it).toString()
        }

    var accessToken: String
        set(value) = sharedPreferences.put(PREF_SESSION_ACCESS_TOKEN, value)
        get() = sharedPreferences.get(PREF_SESSION_ACCESS_TOKEN, String::class.java)

    var isSessionInvalid: Boolean
        set(value) = sharedPreferences.put(SESSION_INVALID, value)
        get() = sharedPreferences.get(SESSION_INVALID, Boolean::class.java)

    fun isLoggedIn(): Boolean {
        return accessToken.isNotEmpty()
    }

    fun getSessionState(): SessionState {
        if (isLoggedIn()) {
            return if (isSessionInvalid) {
                SessionState.INVALID_SESSION
            } else {
                SessionState.LOGGED_IN
            }
        }
        return SessionState.NOT_LOGGED
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object{
        const val PREFS_NAME = "MovieAppSharedPreferences"

        private const val PREFIX_ACCESS_TOKEN = "Bearer"
        private const val PREFIX = "MovieApp_"
        private const val PREF_SESSION_ACCESS_TOKEN = PREFIX + "access_token"
        private const val SESSION_INVALID = "sessionInvalid"
        fun providePreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    }

}

