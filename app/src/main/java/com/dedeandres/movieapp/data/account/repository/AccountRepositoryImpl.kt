package com.dedeandres.movieapp.data.account.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.Either.Left
import com.dedeandres.movieapp.common.Either.Right
import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.common.NetworkException
import com.dedeandres.movieapp.data.account.api.AccountApi
import com.dedeandres.movieapp.data.account.entity.LoginRequestDto
import com.dedeandres.movieapp.data.account.entity.mapToModel
import com.dedeandres.movieapp.domain.account.entity.LoginResponseModel
import com.dedeandres.movieapp.domain.account.entity.UserResponseModel
import com.dedeandres.movieapp.domain.account.repository.AccountRepository
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