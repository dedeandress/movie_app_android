package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.usecase

import com.dedeandres.scaffoldproject.common.KeyUtils
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.None
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseUseCase
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.SessionRepository
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<None>() {
    override suspend fun buildUseCaseSingle(data: Map<String, Any?>): Either<Exception, None> {
        return sessionRepository.setToken(data[KeyUtils.TOKEN] as String)
    }
}