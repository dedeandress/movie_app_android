package com.dedeandres.movieapp.data.movie.entity

import com.dedeandres.movieapp.domain.movie.entity.MovieModel
import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: List<MovieBean>,
    @SerializedName("total_page")
    val totalPage: Int,
    @SerializedName("total_result")
    val totalResult: Int
) {
    data class MovieBean(
        val id: Int,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("vote_average")
        val voteAverage: Float?,
        @SerializedName("original_title")
        val originalTitle: String?
    )
}

fun MovieResponseDto.mapToModel(): List<MovieModel> {
    return result.map {
        MovieModel(
            id = it.id.toString(),
            posterPath = it.posterPath ?: "",
            originalTitle = it.originalTitle ?: "",
            voteAverage = it.voteAverage.toString()
        )
    }
}