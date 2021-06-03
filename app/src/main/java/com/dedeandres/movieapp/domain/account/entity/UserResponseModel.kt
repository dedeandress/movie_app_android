package com.dedeandres.movieapp.domain.account.entity

import com.dedeandres.movieapp.presenter.account.login.entity.UserResult

data class UserResponseModel(
    val id: String,
    val email: String
)

fun UserResponseModel.mapToResult() = UserResult(
    id = this.id,
    email = this.email
)