package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.usecase

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseUseCase
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.map
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.mapToResult
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.AccountRepository
import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.login.entity.UserResult
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<UserResult>() {
    override suspend fun buildUseCaseSingle(data: Map<String, Any?>): Either<Exception, UserResult> {
        return accountRepository.me().map {
            it.mapToResult()
        }
    }

}