package com.dedeandres.movieapp.di.repository

import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.common.services
import com.dedeandres.movieapp.data.movie.api.MovieApi
import com.dedeandres.movieapp.data.movie.repository.MovieRepositoryImpl
import com.dedeandres.movieapp.domain.movie.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {

    @Provides
    fun provideMovieApi(
        retrofit: Retrofit
    ): MovieApi {
        return services(retrofit)
    }

    @Provides
    fun provideMovieRepository(
        movieApi: MovieApi,
        httpErrorHandler: HttpErrorHandler
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi, httpErrorHandler)
    }

}