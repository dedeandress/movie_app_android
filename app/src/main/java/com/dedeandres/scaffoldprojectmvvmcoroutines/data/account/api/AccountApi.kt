package com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.api

import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.LoginRequestDto
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.LoginResponseDto
import com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {

    @POST("v1/auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @GET("api/v1/users/me")
    suspend fun getMe(): UserResponseDto

}