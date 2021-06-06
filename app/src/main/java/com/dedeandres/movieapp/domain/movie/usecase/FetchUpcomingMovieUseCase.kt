package com.dedeandres.movieapp.domain.movie.usecase

import com.dedeandres.movieapp.BuildConfig
import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.common.map
import com.dedeandres.movieapp.domain.movie.entity.mapToResult
import com.dedeandres.movieapp.domain.movie.repository.MovieRepository
import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult
import javax.inject.Inject

class FetchUpcomingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<List<MovieResult>>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, List<MovieResult>> {
        return movieRepository.getUpcoming(
            apiKey = BuildConfig.API_KEY,
            language = "en-US",
            region = "ID"
        ).map {
            it.mapToResult()
        }
    }

}