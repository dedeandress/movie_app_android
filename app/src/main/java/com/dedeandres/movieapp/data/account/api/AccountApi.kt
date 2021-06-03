package com.dedeandres.movieapp.data.account.api

import com.dedeandres.movieapp.data.account.entity.LoginRequestDto
import com.dedeandres.movieapp.data.account.entity.LoginResponseDto
import com.dedeandres.movieapp.data.account.entity.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {

    @POST("v1/auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @GET("api/v1/users/me")
    suspend fun getMe(): UserResponseDto

}