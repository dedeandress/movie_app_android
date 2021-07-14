package com.dedeandres.movieapp.data.movie.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.data.movie.api.MovieApi
import com.dedeandres.movieapp.data.movie.entity.mapToModel
import com.dedeandres.movieapp.domain.movie.entity.MovieCreditModel
import com.dedeandres.movieapp.domain.movie.entity.MovieDetailModel
import com.dedeandres.movieapp.domain.movie.entity.MovieModel
import com.dedeandres.movieapp.domain.movie.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val httpErrorHandler: HttpErrorHandler
) : MovieRepository {
    override suspend fun getNowPlaying(
        apiKey: String,
        language: String,
        region: String
    ): Either<Exception, List<MovieModel>> {
        return try {
            Either.Right(movieApi.getNowPlaying(apiKey, language, region).mapToModel())
        } catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }

    override suspend fun getTopRated(
        apiKey: String,
        language: String,
        region: String
    ): Either<Exception, List<MovieModel>> {
        return try {
            Either.Right(movieApi.getTopRated(apiKey, language, region).mapToModel())
        } catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }

    override suspend fun getUpcoming(
        apiKey: String,
        language: String,
        region: String
    ): Either<Exception, List<MovieModel>> {
        return try {
            Either.Right(movieApi.getUpcoming(apiKey, language, region).mapToModel())
        } catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        region: String,
        movieId: String
    ): Either<Exception, MovieDetailModel> {
        return try {
            Either.Right(movieApi.getMovieDetail(apiKey = apiKey, language = language, region = region, movieId = movieId).mapToModel())
        } catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }

    override suspend fun getMovieCredit(
        apiKey: String,
        language: String,
        region: String,
        movieId: String
    ): Either<Exception, MovieCreditModel> {
        return try {
            Either.Right(movieApi.getMovieCredit(apiKey = apiKey, language = language, region = region, movieId = movieId).mapToModel())
        } catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }
}