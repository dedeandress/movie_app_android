package com.dedeandres.movieapp.domain.movie.entity

import com.dedeandres.movieapp.data.movie.entity.Genres
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.MovieDetailResult

data class MovieDetailModel(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val voteAverage: String,
    val voteCount: String
)

fun MovieDetailModel.mapToResult(): MovieDetailResult = MovieDetailResult(
    adult,
    backdropPath,
    budget,
    genres,
    homepage,
    id,
    imdbId,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    revenue,
    voteAverage,
    voteCount
)