package com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("email")
    private val email : String,
    @SerializedName("password")
    private val password : String
)