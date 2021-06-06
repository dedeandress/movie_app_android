package com.dedeandres.movieapp.presenter.movie.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dedeandres.scaffoldproject.common.Event
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.setErrorEvent
import com.dedeandres.movieapp.common.setLoadingEvent
import com.dedeandres.movieapp.common.setSuccessEvent
import com.dedeandres.movieapp.domain.genre.usecase.FetchGenreListUseCase
import com.dedeandres.movieapp.domain.movie.usecase.FetchNowPlayingMovieUseCase
import com.dedeandres.movieapp.domain.movie.usecase.FetchTopRatedMovieUseCase
import com.dedeandres.movieapp.domain.movie.usecase.FetchUpcomingMovieUseCase
import com.dedeandres.movieapp.presenter.movie.movielist.entity.GenreResult
import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val fetchGenreListUseCase: FetchGenreListUseCase,
    private val fetchNowPlayingMovieUseCase: FetchNowPlayingMovieUseCase,
    private val fetchTopRatedMovieUseCase: FetchTopRatedMovieUseCase,
    private val fetchUpcomingMovieUseCase: FetchUpcomingMovieUseCase
) : ViewModel() {

    val fetchGenreListLiveData = MutableLiveData<Event<Resource<List<GenreResult>>>>()
    val fetchMovieListLiveData = MutableLiveData<Event<Resource<List<MovieResult>>>>()

    fun fetchGenreList() {
        fetchGenreListLiveData.setLoadingEvent()

        fetchGenreListUseCase {
            it.fold(
                { exception ->
                    fetchGenreListLiveData.setErrorEvent(exception)
                },
                { genreList ->
                    fetchGenreListLiveData.setSuccessEvent(genreList)
                }
            )
        }
    }

    fun fetchNowPlayingMovie() {
        fetchMovieListLiveData.setLoadingEvent()

        fetchNowPlayingMovieUseCase {
            it.fold(
                { exception ->
                  fetchMovieListLiveData.setErrorEvent(exception)
                },
                { movieList ->
                    fetchMovieListLiveData.setSuccessEvent(movieList)
                }
            )
        }
    }

    fun fetchTopRatedMovie() {
        fetchMovieListLiveData.setLoadingEvent()

        fetchTopRatedMovieUseCase {
            it.fold(
                { exception ->
                    fetchMovieListLiveData.setErrorEvent(exception)
                },
                { movieList ->
                    fetchMovieListLiveData.setSuccessEvent(movieList)
                }
            )
        }
    }

    fun fetchUpcomingMovie() {
        fetchMovieListLiveData.setLoadingEvent()

        fetchUpcomingMovieUseCase {
            it.fold(
                { exception ->
                    fetchMovieListLiveData.setErrorEvent(exception)
                },
                { movieList ->
                    fetchMovieListLiveData.setSuccessEvent(movieList)
                }
            )
        }
    }



}