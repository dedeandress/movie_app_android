package com.dedeandres.movieapp.domain.genre.usecase

import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.common.map
import com.dedeandres.movieapp.domain.genre.entity.mapToResult
import com.dedeandres.movieapp.domain.genre.repository.GenreRepository
import com.dedeandres.movieapp.presenter.movie.movielist.entity.GenreResult
import javax.inject.Inject

class FetchGenreListUseCase @Inject constructor(private val genreRepository: GenreRepository) :
    BaseUseCase<List<GenreResult>>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, List<GenreResult>> {
        return genreRepository.getGenres().map {
            it.mapToResult()
        }
    }

}