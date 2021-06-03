package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.None
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.SessionState

interface SessionRepository {

    suspend fun isLoggedIn(): Either<Exception, Boolean>

    suspend fun getSessionState(): Either<Exception, SessionState>

    suspend fun setToken(token: String): Either<Exception, None>

}