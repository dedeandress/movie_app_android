package com.dedeandres.movieapp.domain.genre.repository

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.domain.genre.entity.GenreModel

interface GenreRepository {
    suspend fun getGenres(): Either<Exception, List<GenreModel>>
}