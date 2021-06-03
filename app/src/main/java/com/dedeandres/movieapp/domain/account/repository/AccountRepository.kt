package com.dedeandres.movieapp.domain.account.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.data.account.entity.LoginRequestDto
import com.dedeandres.movieapp.domain.account.entity.LoginResponseModel
import com.dedeandres.movieapp.domain.account.entity.UserResponseModel
import java.lang.Exception

interface AccountRepository {

    suspend fun login(loginRequestDto: LoginRequestDto): Either<Exception, LoginResponseModel>
    suspend fun me(): Either<Exception, UserResponseModel>
}