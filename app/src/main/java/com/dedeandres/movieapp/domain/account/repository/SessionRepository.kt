package com.dedeandres.movieapp.domain.account.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.None
import com.dedeandres.movieapp.domain.account.entity.SessionState

interface SessionRepository {

    suspend fun isLoggedIn(): Either<Exception, Boolean>

    suspend fun getSessionState(): Either<Exception, SessionState>

    suspend fun setToken(token: String): Either<Exception, None>

}