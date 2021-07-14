package com.dedeandres.movieapp.data.movie.entity

import com.dedeandres.movieapp.domain.movie.entity.MovieDetailModel
import com.google.gson.annotations.SerializedName

data class MovieDetailResponseDto(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<Genres>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int
)

data class Genres(val id: Int, val name: String)

fun MovieDetailResponseDto.mapToModel(): MovieDetailModel = MovieDetailModel(
    adult,
    backdropPath ?: posterPath,
    budget,
    genres,
    homepage,
    id,
    imdbId ?: "",
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    revenue,
    voteAverage.toString(),
    voteCount.toString()
)