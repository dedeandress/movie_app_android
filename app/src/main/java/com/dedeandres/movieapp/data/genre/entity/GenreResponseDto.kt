package com.dedeandres.movieapp.data.genre.entity

import com.dedeandres.movieapp.domain.genre.entity.GenreModel
import com.google.gson.annotations.SerializedName

data class GenreResponseDto(
    @SerializedName("genres")
    val genres: List<GenreBean>
) {
    data class GenreBean(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

fun List<GenreResponseDto.GenreBean>.mapToModel(): List<GenreModel> {
    return this.map {
        it.mapToModel()
    }
}

fun GenreResponseDto.GenreBean.mapToModel(): GenreModel {
    return GenreModel(id = this.id, name = this.name)
}