package com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.repository

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either.Left
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either.Right
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.HttpErrorHandler
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.NetworkException
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.api.AccountApi
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.LoginRequestDto
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.mapToModel
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.LoginResponseModel
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.UserResponseModel
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.AccountRepository
import timber.log.Timber

class AccountRepositoryImpl(
    private val accountApi: AccountApi,
    private val httpErrorHandler: HttpErrorHandler
) : AccountRepository {
    override suspend fun login(loginRequestDto: LoginRequestDto): Either<Exception, LoginResponseModel> {
        Timber.d("login")

        return try {
            Right(accountApi.login(loginRequestDto).mapToModel())
        } catch (e: Exception) {
            Timber.d("Error: $e")
            Left(httpErrorHandler.handleException(e))
        }
    }

    override suspend fun me(): Either<Exception, UserResponseModel> {
        return try {
            Right(accountApi.getMe().mapToModel())
        } catch (e: NetworkException) {
            Timber.d("Error: $e")
            Left(httpErrorHandler.handleException(e))
        }
    }
}