package com.dedeandres.movieapp.domain.movie.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.domain.movie.entity.MovieCreditModel
import com.dedeandres.movieapp.domain.movie.entity.MovieDetailModel
import com.dedeandres.movieapp.domain.movie.entity.MovieModel

interface MovieRepository {

    suspend fun getNowPlaying(
        apiKey: String,
        language: String,
        region: String
    ): Either<Exception, List<MovieModel>>

    suspend fun getTopRated(
        apiKey: String,
        language: String,
        region: String
    ): Either<Exception, List<MovieModel>>

    suspend fun getUpcoming(
        apiKey: String,
        language: String,
        region: String
    ): Either<Exception, List<MovieModel>>

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        region: String,
        movieId: String
    ): Either<Exception, MovieDetailModel>

    suspend fun getMovieCredit(
        apiKey: String,
        language: String,
        region: String,
        movieId: String
    ): Either<Exception, MovieCreditModel>
}