package com.dedeandres.movieapp.domain.account.usecase

import com.dedeandres.scaffoldproject.common.KeyUtils.PASSWORD
import com.dedeandres.scaffoldproject.common.KeyUtils.USERNAME
import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.common.map
import com.dedeandres.movieapp.data.account.entity.LoginRequestDto
import com.dedeandres.movieapp.domain.account.entity.mapToResult
import com.dedeandres.movieapp.domain.account.repository.AccountRepository
import com.dedeandres.movieapp.presenter.account.login.entity.LoginResponseResult
import timber.log.Timber
import javax.inject.Inject

class FetchLoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<LoginResponseResult>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, LoginResponseResult> {
        val username = data[USERNAME] as String
        val password = data[PASSWORD] as String
        return accountRepository.login(LoginRequestDto(username, password)).map {
            Timber.d("FetchLoginUseCase $it")
            it.mapToResult()
        }
    }

}