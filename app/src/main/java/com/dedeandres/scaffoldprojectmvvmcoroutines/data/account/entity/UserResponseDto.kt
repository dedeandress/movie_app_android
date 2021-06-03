package com.dedeandres.scaffoldprojectmvvmcoroutines.data.account.entity

import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.UserResponseModel

data class UserResponseDto(
    val id: String,
    val email: String
)

fun UserResponseDto.mapToModel() = UserResponseModel(
    id = this.id,
    email = this.email
)