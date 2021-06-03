package com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity

import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.login.entity.UserResult

data class UserResponseModel(
    val id: String,
    val email: String
)

fun UserResponseModel.mapToResult() = UserResult(
    id = this.id,
    email = this.email
)