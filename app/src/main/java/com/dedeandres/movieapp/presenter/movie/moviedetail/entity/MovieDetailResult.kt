package com.dedeandres.movieapp.presenter.movie.moviedetail.entity

import com.dedeandres.movieapp.data.movie.entity.Genres

data class MovieDetailResult(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val voteAverage: String,
    val voteCount: String
)