package com.dedeandres.movieapp.data.account.entity

import com.dedeandres.movieapp.domain.account.entity.LoginResponseModel
import com.google.gson.annotations.SerializedName


data class LoginResponseDto(
    @SerializedName("payload")
    val payload: PayloadBean? = null
){
    data class PayloadBean(
        @SerializedName("auth_token")
        val token: String
    )
}

fun LoginResponseDto.mapToModel() = LoginResponseModel(
    token = this.payload?.token ?: ""
)