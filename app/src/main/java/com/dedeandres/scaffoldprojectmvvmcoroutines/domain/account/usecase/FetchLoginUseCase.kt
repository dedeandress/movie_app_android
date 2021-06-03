package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.usecase

import com.dedeandres.scaffoldproject.common.KeyUtils.PASSWORD
import com.dedeandres.scaffoldproject.common.KeyUtils.USERNAME
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseUseCase
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.map
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.LoginRequestDto
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.mapToResult
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.AccountRepository
import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.login.entity.LoginResponseResult
import timber.log.Timber
import javax.inject.Inject

class FetchLoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<LoginResponseResult>() {
    override suspend fun buildUseCaseSingle(data: Map<String, Any?>): Either<Exception, LoginResponseResult> {
        val username = data[USERNAME] as String
        val password = data[PASSWORD] as String
        return accountRepository.login(LoginRequestDto(username, password)).map {
            Timber.d("FetchLoginUseCase $it")
            it.mapToResult()
        }
    }

}