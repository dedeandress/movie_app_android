package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity

import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.login.entity.LoginResponseResult

data class LoginResponseModel(
    val token: String
)

fun LoginResponseModel.mapToResult() = LoginResponseResult(
    token = this.token
)