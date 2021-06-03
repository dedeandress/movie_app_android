package com.dedeandres.movieapp.data.account.entity

import com.dedeandres.movieapp.domain.account.entity.UserResponseModel

data class UserResponseDto(
    val id: String,
    val email: String
)

fun UserResponseDto.mapToModel() = UserResponseModel(
    id = this.id,
    email = this.email
)