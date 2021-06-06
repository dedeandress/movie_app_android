package com.dedeandres.movieapp.domain.movie.entity

import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult

data class MovieModel(
    val id: String,
    val originalTitle: String,
    val posterPath: String,
    val voteAverage: String
)

fun MovieModel.mapToResult(): MovieResult {
    return MovieResult(
        id = id,
        posterPath = posterPath,
        voteAverage = voteAverage,
        originalTitle = originalTitle
    )
}

fun List<MovieModel>.mapToResult(): List<MovieResult> {
    return map {
        it.mapToResult()
    }
}