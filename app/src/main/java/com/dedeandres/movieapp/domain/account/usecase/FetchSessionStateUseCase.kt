package com.dedeandres.movieapp.domain.account.usecase

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.domain.account.entity.SessionState
import com.dedeandres.movieapp.domain.account.repository.SessionRepository
import javax.inject.Inject

class FetchSessionStateUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<SessionState>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, SessionState> {
        return sessionRepository.getSessionState()
    }

}