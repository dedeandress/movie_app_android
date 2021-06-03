package com.dedeandres.movieapp.data.genre.repository

import com.dedeandres.movieapp.BuildConfig
import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.data.genre.api.GenreApi
import com.dedeandres.movieapp.data.genre.entity.mapToModel
import com.dedeandres.movieapp.domain.genre.entity.GenreModel
import com.dedeandres.movieapp.domain.genre.repository.GenreRepository

class GenreRepositoryImpl(private  val genreApi: GenreApi, private val httpErrorHandler: HttpErrorHandler) : GenreRepository {
    override suspend fun getGenres(): Either<Exception, List<GenreModel>> {
        return try {
            Either.Right(genreApi.getGenres(BuildConfig.API_KEY).genres.mapToModel())
        } catch (e: Exception) {
            Either.Left(httpErrorHandler.handleException(e))
        }
    }
}