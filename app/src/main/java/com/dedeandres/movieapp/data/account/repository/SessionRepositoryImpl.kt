package com.dedeandres.movieapp.data.account.repository

import com.dedeandres.movieapp.common.*
import com.dedeandres.movieapp.common.Either.Left
import com.dedeandres.movieapp.common.Either.Right
import com.dedeandres.movieapp.common.sharedpref.SharedPrefs
import com.dedeandres.movieapp.domain.account.entity.SessionState
import com.dedeandres.movieapp.domain.account.repository.SessionRepository

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