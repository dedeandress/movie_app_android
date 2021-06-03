package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.usecase

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseUseCase
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.SessionState
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.SessionRepository
import javax.inject.Inject

class FetchSessionStateUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<SessionState>() {
    override suspend fun buildUseCaseSingle(data: Map<String, Any?>): Either<Exception, SessionState> {
        return sessionRepository.getSessionState()
    }

}