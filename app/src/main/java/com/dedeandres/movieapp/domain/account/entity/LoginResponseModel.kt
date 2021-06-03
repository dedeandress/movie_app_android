package com.dedeandres.movieapp.domain.account.entity

import com.dedeandres.movieapp.presenter.account.login.entity.LoginResponseResult

data class LoginResponseModel(
    val token: String
)

fun LoginResponseModel.mapToResult() = LoginResponseResult(
    token = this.token
)