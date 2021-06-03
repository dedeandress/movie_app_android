package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.LoginRequestDto
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.LoginResponseModel
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.UserResponseModel
import java.lang.Exception

interface AccountRepository {

    suspend fun login(loginRequestDto: LoginRequestDto): Either<Exception, LoginResponseModel>
    suspend fun me(): Either<Exception, UserResponseModel>
}