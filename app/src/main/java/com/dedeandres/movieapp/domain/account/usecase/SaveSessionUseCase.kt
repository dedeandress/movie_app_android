package com.dedeandres.movieapp.domain.account.usecase

import com.dedeandres.scaffoldproject.common.KeyUtils
import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.None
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.domain.account.repository.SessionRepository
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<None>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, None> {
        return sessionRepository.setToken(data[KeyUtils.TOKEN] as String)
    }
}