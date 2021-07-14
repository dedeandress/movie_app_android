package com.dedeandres.movieapp.domain.movie.usecase

import com.dedeandres.movieapp.BuildConfig
import com.dedeandres.movieapp.common.Either
import com.dedeandres.movieapp.common.base.BaseUseCase
import com.dedeandres.movieapp.common.map
import com.dedeandres.movieapp.domain.movie.entity.mapToResult
import com.dedeandres.movieapp.domain.movie.repository.MovieRepository
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.CastCrewResult
import javax.inject.Inject

class FetchMovieCreditUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<List<CastCrewResult>>() {
    override suspend fun buildUseCase(data: Map<String, Any?>): Either<Exception, List<CastCrewResult>> {
        val movieId = data[MOVIE_ID] as String
        return movieRepository.getMovieCredit(
            apiKey = BuildConfig.API_KEY,
            language = "en-US",
            region = "ID",
            movieId
        ).map {
            it.mapToResult()
        }
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }
}