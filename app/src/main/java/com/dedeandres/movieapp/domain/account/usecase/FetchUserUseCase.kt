package com.dedeandres.movieapp.domain.account.usecase

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.common.map
import com.dedeandres.movieapp.domain.account.entity.mapToResult
import com.dedeandres.movieapp.domain.account.repository.AccountRepository
import com.dedeandres.movieapp.presenter.account.login.entity.UserResult
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<UserResult>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, UserResult> {
        return accountRepository.me().map {
            it.mapToResult()
        }
    }

}