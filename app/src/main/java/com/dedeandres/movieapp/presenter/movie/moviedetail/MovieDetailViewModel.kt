package com.dedeandres.movieapp.presenter.movie.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.setErrorEvent
import com.dedeandres.movieapp.common.setLoadingEvent
import com.dedeandres.movieapp.common.setSuccessEvent
import com.dedeandres.movieapp.domain.movie.usecase.FetchMovieCreditUseCase
import com.dedeandres.movieapp.domain.movie.usecase.FetchMovieDetailUseCase
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.CastCrewResult
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.MovieDetailResult
import com.dedeandres.scaffoldproject.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchMovieCreditUseCase: FetchMovieCreditUseCase
) : ViewModel() {

    val fetchMovieDetailLiveData = MutableLiveData<Event<Resource<MovieDetailResult>>>()
    val fetchMovieCreditLiveData = MutableLiveData<Event<Resource<List<CastCrewResult>>>>()

    fun fetchMovieDetail(movieId: String) {
        fetchMovieDetailLiveData.setLoadingEvent()

        fetchMovieDetailUseCase(mapOf(FetchMovieDetailUseCase.MOVIE_ID to movieId)) {
            it.fold(
                { exception ->
                    fetchMovieDetailLiveData.setErrorEvent(exception)
                },
                { result ->
                    fetchMovieDetailLiveData.setSuccessEvent(result)
                }
            )
        }
    }

    fun fetchMovieCredit(movieId: String) {
        fetchMovieCreditLiveData.setLoadingEvent()

        fetchMovieCreditUseCase(mapOf(FetchMovieCreditUseCase.MOVIE_ID to movieId)) {
            it.fold(
                { exception ->
                    fetchMovieCreditLiveData.setErrorEvent(exception)
                },
                { result ->
                    fetchMovieCreditLiveData.setSuccessEvent(result)
                }
            )
        }
    }
}