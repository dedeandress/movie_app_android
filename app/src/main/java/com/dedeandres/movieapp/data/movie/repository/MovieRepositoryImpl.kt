package com.dedeandres.movieapp.data.movie.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.data.movie.api.MovieApi
import com.dedeandres.movieapp.data.movie.entity.mapToModel
import com.dedeandres.movieapp.domain.movie.entity.MovieModel
import com.dedeandres.movieapp.domain.movie.repository.MovieRepository

class MovieRepositoryImpl(private val movieApi: MovieApi, private val httpErrorHandler: HttpErrorHandler) : MovieRepository {
    override suspend fun getNowPlaying(apiKey: String, language: String, region: String): Either<Exception, List<MovieModel>> {
        return try {
            Either.Right(movieApi.getNowPlaying(apiKey, language, region).mapToModel())
        }catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }
}