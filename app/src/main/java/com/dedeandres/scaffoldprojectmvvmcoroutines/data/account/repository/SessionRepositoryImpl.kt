package com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.repository

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.*
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either.Left
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either.Right
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.sharedpref.SharedPrefs
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.SessionState
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.SessionRepository

class SessionRepositoryImpl(
    private val sharedPrefs: SharedPrefs
) : SessionRepository {
    override suspend fun isLoggedIn(): Either<Exception, Boolean> {
        return try {
            Right(sharedPrefs.isLoggedIn())
        } catch (e: Exception) {
            Left(CacheException("Cache Exception"))
        }
    }

    override suspend fun getSessionState(): Either<Exception, SessionState> {
        return try {
            Right(sharedPrefs.getSessionState())
        } catch (e: NetworkException) {
            Left(CacheException("Cache Exception"))
        }
    }

    override suspend fun setToken(token: String): Either<Exception, None> {
        return try {
            sharedPrefs.accessToken = token
            Right(None)
        } catch (e: NetworkException) {
            Left(CacheException("Cache Exception"))
        }
    }
}